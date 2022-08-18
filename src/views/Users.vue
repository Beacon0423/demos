<template>
  <el-container style="height: 100%">
    <!--侧边栏-->
    <el-aside width="250px" style="background-color: rgb(238, 241, 246);height: 100%">
      <!--设置背景颜色，设置字体颜色，设置选中字体颜色-->
      <!--overflow-x: hidden超出部分隐藏-->
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
               background-color="rgb(48,65,86)"
               text-color="#fff"
               active-text-color="#ffd04b">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>导航一</template>
          <el-menu-item-group title="分组1">
            <el-menu-item index="1-3">选项1</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项2</template>
            <el-menu-item index="1-4-1">选项2-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>导航二</template>
          <el-menu-item-group title="分组1">
            <el-menu-item index="1-3">选项1</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项2</template>
            <el-menu-item index="1-4-1">选项2-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-setting"></i>导航三</template>
          <el-menu-item-group title="分组1">
            <el-menu-item index="1-3">选项1</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项2</template>
            <el-menu-item index="1-4-1">选项2-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>
    <!--主体容器-->
    <el-container>
      <!--头部-->
      <el-header style="text-align: right; font-size: 12px; border: 1px solid #ccc; line-height: 60px">
        <!--border(边界)定义1px的solid(实线)颜色-->
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人中心</el-dropdown-item>
            <el-dropdown-item>退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>王小虎</span>
      </el-header>
      <!--主体-->
      <el-main>
        <!--搜索-->
        <div style="margin: 10px 0">
          <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
          <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" class="ml-5"
                    v-model="email"></el-input>
          <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5"
                    v-model="address"></el-input>
          <el-button class="ml-5" type="primary" icon="el-icon-search" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <div style="margin: 10px 0">
          <el-button type="primary" @click="handleAdd">新增
            <li class="el-icon-circle-plus-outline"></li>
          </el-button>
          <el-popconfirm
            style="margin: 0px 10px"
            confirm-button-text='确定'
            cancel-button-text='我再想想'
            icon="el-icon-info"
            icon-color="red"
            title="您确定批量删除这些数据吗？"
            @confirm="delBatch"
          >
            <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
          <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
        </div>
        <!--表格-->
        <el-table :data="tableData" stripe @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="ID" width="140">
          </el-table-column>
          <el-table-column prop="username" label="姓名" width="140">
          </el-table-column>
          <el-table-column prop="nickname" label="昵称" width="120">
          </el-table-column>
          <el-table-column prop="email" label="邮箱">
          </el-table-column>
          <el-table-column prop="phone" label="电话">
          </el-table-column>
          <el-table-column prop="address" label="地址">
          </el-table-column>
          <el-table-column prop="" label="操作" width="200px" align="center">
            <template slot-scope="scope">
              <el-button type="success" @click="handleEdit(scope.row)">编辑</el-button>
              <el-popconfirm class="ml-5"
                             confirm-button-text='确定'
                             cancel-button-text='我再想想'
                             icon="el-icon-info"
                             icon-color="red"
                             title="您确定删除吗？"
                             @confirm="del(scope.row.id)">
                <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <!--分页查询-->
        <div style="padding: 20px 0px">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :page-sizes="[2, 5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
        </div>

        <!--信息弹窗-->
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
          <el-form label-width="80px" size="small">
            <el-form-item label="用户名">
              <el-input v-model="form.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="昵称">
              <el-input v-model="form.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址">
              <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

export default {
  name: 'Users',
  data () {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      username: '',
      nickname: '',
      email: '',
      address: '',
      form: {},
      dialogFormVisible: false,
      multipleSelection: []
    }
  },
  created () {
    // 请求分页查询数据
    this.load()
  },
  methods: {
    load () {
      let www
      www.get('/user/findPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          nickname: this.nickname,
          email: this.email,
          address: this.address
        }
      }).then(res => {
        console.log(res)
        this.total = res.total
        this.tableData = res.records
      })
    },
    // 分页查询
    handleSizeChange (pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange (pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    // 重置按钮方法
    reset () {
      this.username = ''
      this.email = ''
      this.address = ''
      this.load()
    },
    // 新增弹窗
    handleAdd () {
      this.dialogFormVisible = true
      this.form = {}
    },
    // 编辑
    handleEdit (row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    // 保存
    save () {
      let www
      www.post('/user/save', this.form).then(res => {
        if (res) {
          this.$message.success('保存成功')
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error('保存失败')
        }
      })
    },
    // 根据id删除
    del (id) {
      let www
      www.delete('/user/delete/' + id).then(res => {
        if (res) {
          this.$message.success('删除成功')
          this.load()
        } else {
          this.$message.error('删除失败')
        }
      })
    },
    // 选中的数据
    handleSelectionChange (val) {
      console.log(val)
      this.multipleSelection = val
    },
    // 批量删除
    delBatch () {
      let ids = this.multipleSelection.map(v => v.id) // 把选中的对象转成[{}, {}, {}] => [1,2,3]
      let www
      www.post('/user/del/batch', ids).then(res => {
        if (res) {
          this.$message.success('批量删除成功')
          this.load()
        } else {
          this.$message.error('批量删除失败')
        }
      })
    }

  }

}
</script>

<style>

</style>
