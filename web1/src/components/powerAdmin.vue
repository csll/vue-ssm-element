<template>
    <div>
      <el-breadcrumb separator="/" class="bread">
        <el-breadcrumb-item :to="{ name: 'welcome' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>权限管理</el-breadcrumb-item>
        <el-breadcrumb-item>设置权限</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card>
        <el-row :gutter="20" style="margin-bottom: 10px">
          <el-col :span="1">
            <el-button type="primary" plain @click="AddRoleDialogVisible=true" style="width: 200px;">添加角色</el-button>
          </el-col>
        </el-row>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          label="用户角色"
          width="180">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>级别: {{ scope.row.roleName }}</p>
              <p>描述: {{ scope.row.describe }}</p>
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.roleName }}</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          label="角色描述"
          width="400">
          <template slot-scope="scope">
            <i class="el-icon-user"></i>
            <span style="margin-left: 10px">{{ scope.row.describe }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope" >
            <el-button
              v-if="scope.row.roleName==='超级管理员'"
              size="mini"
              disabled
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              v-else
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
            size="mini"
            v-if="scope.row.roleName==='超级管理员'"
            type="danger"
            disabled
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            <el-button
              size="mini"
              v-else
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      </el-card>

      <!-- 添加角色对话框 -->
      <el-dialog title="添加角色" :visible.sync="AddRoleDialogVisible" width="40%" @close="addRoleDialogClosed">
        <el-form
          :model="addRoleForm"
          ref="addRoleFormRef"
          :rules="addRoleFormRules"
          label-width="100px"
        >
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="addRoleForm.roleName"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="describe">
            <el-input v-model="addRoleForm.describe"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="AddRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRoles">确 定</el-button>
      </span>
      </el-dialog>
    </div>
</template>

<script>
export default {
  created () {
    this.getRoleList()
  },
  data () {
    return {
      tableData: [{
        roleName: '超级管理员',
        describe: '拥有该管理系统最高权限'
      }],
      //   添加用户对话框
      AddRoleDialogVisible: false,
      // 添加角色表单
      addRoleForm: {},
      //   添加角色表单验证
      addRoleFormRules: {
        roleName: [
          { required: true, message: '请输入角色名称', trigger: 'blur' }
        ],
        describe: [
          { required: true, message: '请输入角色描述', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(index, row)
    },

    // 获取角色列表
    getRoleList () {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://127.0.0.1:8008/api/private/v1/roles/roleList'
      }).then(function (response) {
        if (response.data.meta.status !== 200) {
          return vm.$message.error('获取用户列表失败！')
        }
        vm.tableData = response.data.data
      })
    },
    // 添加角色对话框的关闭
    addRoleDialogClosed () {
      this.$refs.addRoleFormRef.resetFields()
    },
    // 添加角色
    addRoles () {
      var vm = this
      this.axios({
        method: 'post',
        url: 'http://127.0.0.1:8008/api/private/v1/roles/addRole',
        data: {
          roleName: vm.addRoleForm.roleName,
          describe: vm.addRoleForm.describe
        }
      }).then(res => {
        console.log(res)
        if (res.data.meta.status === 404) {
          vm.$message.warning('请勿重复添加角色！')
        } else if (res.data.meta.status === 400) {
          vm.$message.error('添加角色失败')
        } else {
          // 隐藏添加用户对话框
          vm.$message.success('添加角色成功！')
        }

        vm.AddRoleDialogVisible = false
        vm.getRolesList()
      })
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
