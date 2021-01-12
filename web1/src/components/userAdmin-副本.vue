<template>
  <div>
    <el-breadcrumb separator="/" class="bread">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
    <!-- 搜索 添加 -->
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="6">
        <el-input placeholder="请输入内容"  v-model="queryInfo.query" clearable >
          <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
      </el-col>
    </el-row>
  <el-table
    :data="tableData"
    border
    stripe
    style="width: 100%">
    <el-table-column
      fixed
      prop="id"
      label="用户ID"
      width="150">
    </el-table-column>
    <el-table-column
      prop="username"
      label="用户名称"
      width="120">
    </el-table-column>
    <el-table-column
      prop="roleName"
      label="身份"
      width="120">
    </el-table-column>
    <el-table-column
      label="用户状态"
      width="120">
      <template slot-scope="scope">
        <el-switch v-model="scope.row.mgState" @change="userStateChanged(scope.row)"></el-switch>
      </template>
    </el-table-column>
    <el-table-column
      prop="mobile"
      label="手机号码"
      width="120">
    </el-table-column>
    <el-table-column
      prop="email"
      label="邮箱"
      width="300">
    </el-table-column>
    <el-table-column
      prop="createTime"
      label="创建时间"
      width="180">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="120">
      <template slot-scope="scope">
        <!--<el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
        <el-button type="text" size="small" style="margin-right: 10px" @click="showEditDialog(scope.row)">编辑</el-button>
        <el-popover
          placement="top-start"
          title="删除"
          width="200"
          trigger="hover"
          content="您正在进行删除用户操作">
        <el-button type="text" slot="reference" size="small" @click="deleteById(scope.$index,tableData,scope.row)">删除</el-button>
        </el-popover>
      </template>
    </el-table-column>
  </el-table>
      <!-- 分页区域 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[2, 5, 10, 15]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totle"
      ></el-pagination>
    </el-card>

    <!-- 添加用户的对话框 -->
    <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addUserForm"
        ref="addUserFormRef"
        :rules="addUserFormRules"
        label-width="100px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addUserForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码"  prop="password">
          <el-input v-model="addUserForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="addUserForm.mobile"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改用户的对话框 -->
    <el-dialog
      title="修改用户信息"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <!-- 内容主体 -->
      <el-form
        :model="editUserForm"
        ref="editUserFormRef"
        :rules="editUserFormRules"
        label-width="70px"
      >
        <el-form-item label="用户名">
          <el-input v-model="editUserForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="editUserForm.mobile"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'userAdmin',

  created () {
    this.getUserList()
  },
  methods: {
    handleClick (row) {
      console.log(row)
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editUserFormRef.resetFields()
    },
    // 监听 添加用户对话框的关闭事件
    addDialogClosed () {
      this.$refs.addUserFormRef.resetFields()
    },

    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getUserList()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getUserList()
    },

    // 添加用户
    addUser () {
      const vm = this
      // 提交请求前，表单预验证
      this.$refs.addUserFormRef.validate(async valid => {
        // console.log(valid)
        // 表单预校验失败
        if (!valid) return
        console.log(this.addUserForm)
        // 隐藏添加用户对话框
        this.axios({
          method: 'POST',
          url: 'http://127.0.0.1:8008/api/private/v1/users/addUser',
          data: {
            username: this.addUserForm.username,
            password: this.addUserForm.password,
            email: this.addUserForm.email,
            mobile: this.addUserForm.mobile
          }
        }).then(res => {
          if (res.data.meta.status !== 200) {
            vm.$message.error('添加用户失败！')
          }
          // 隐藏添加用户对话框
          vm.editDialogVisible = false
          vm.$message.success('添加用户成功！')
          vm.getUserList()
        })
        this.addDialogVisible = false
        this.getUserList()
      })
    },

    // 修改用户状态
    userStateChanged (userInfo) {
      // console.log(userInfo)
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://127.0.0.1:8008/api/private/v1/users/type',
        params: {
          id: userInfo.id,
          type: userInfo.mgState
        }
      }).then(function (res) {
        if (res.data.meta.status === 200) {
          vm.$message.success('用户状态修改成功')
        } else {
          vm.$message.warning('用户状态修改失败')
        }
      })
    },

    // 删除指定用户
    async deleteById (index, tableData, row) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除 "' + row.username + '" 用户, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)

      // 点击确定 返回值为：confirm
      // 点击取消 返回值为： cancel
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }

      const vm = this
      console.log(index)
      this.axios({
        method: 'GET',
        url: 'http://127.0.0.1:8008/api/private/v1/users/delete?id=' + row.id
      }).then(function (response) {
        if (response.data.meta.status === 402) {
          console.log(response.data.meta.status)
          vm.updateError()
        } else {
          vm.getUserList()
          console.log(row.id)
          vm.getUserListuccess('删除用户成功')
        }
      })
    },
    // 编辑用户信息
    showEditDialog (user) {
      this.editUserForm = user
      console.log(this.editUserForm)
      this.editDialogVisible = true
    },

    // 修改用户信息
    editUser () {
      const vm = this
      // 提交请求前，表单预验证
      this.$refs.editUserFormRef.validate(async valid => {
        // console.log(valid)
        // 表单预校验失败
        if (!valid) {
          vm.$message.error('请输入正确的信息')
          return
        }

        this.axios({
          method: 'POST',
          url: 'http://127.0.0.1:8008/api/private/v1/users/updateUser',
          data: {
            id: vm.editUserForm.id,
            email: vm.editUserForm.email,
            mobile: vm.editUserForm.mobile
          }
        }).then(res => {
          if (res.data.meta.status !== 200) {
            vm.$message.error('更新用户信息失败！')
          }
          // 隐藏添加用户对话框
          vm.editDialogVisible = false
          vm.$message.success('更新用户信息成功！')
          vm.getUserList()
        })
      })
    },

    // 更新列表
    getUserList () {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://127.0.0.1:8008/api/private/v1/users/userList',
        params: {
          query: vm.queryInfo.query,
          pageNum: vm.queryInfo.pagenum,
          pageSize: vm.queryInfo.pagesize
        }
      }).then(function (response) {
        console.log(response)
        if (response.data.meta.status !== 200) {
          return vm.$message.error('获取用户列表失败！')
        }
        console.log(response.data)
        vm.tableData = response.data.data.users
        vm.totle = response.data.data.totalPage
      })
    }
  },

  data () {
    // 自定义邮箱规则
    var checkEmail = (rule, value, callback) => {
      const regEmail = /^\w+@\w+(\.\w+)+$/
      if (regEmail.test(value)) {
        // 合法邮箱
        return callback()
      }
      callback(new Error('请输入合法邮箱'))
    }
    // 自定义手机号规则
    var checkMobile = (rule, value, callback) => {
      const regMobile = /^1[34578]\d{9}$/
      if (regMobile.test(value)) {
        return callback()
      }
      // 返回一个错误提示
      callback(new Error('请输入合法的手机号码'))
    }

    return {
      tableData: [{
        id: 123,
        username: '王小虎',
        mobile: '13888886988',
        email: '1252@qq.com',
        createTime: '2021-01-08 16:37:52',
        mgState: true,
        roleName: '超级管理员'
      }],
      // 获取用户列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      totle: 0,
      // 添加用户对话框
      addDialogVisible: false,
      // 用户添加
      addUserForm: {
        username: '',
        password: '',
        email: '',
        mobile: ''
      },
      // 用户添加表单验证规则
      addUserFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            min: 2,
            max: 10,
            message: '用户名的长度在2～10个字',
            trigger: 'blur'
          }
        ],
        password: [
          { required: true, message: '请输入用户密码', trigger: 'blur' },
          {
            min: 6,
            max: 18,
            message: '用户密码的长度在6～18个字',
            trigger: 'blur'
          }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ]
      },
      // 修改用户
      editDialogVisible: false,
      editUserForm: {},
      // 编辑用户表单验证
      editUserFormRules: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ]
      }
    }
  }
}
</script>

<style scoped>
  .footer {
    height: 30px;
    font-size: 18px;
    background-color: white;
    text-align: left;
  }

</style>
