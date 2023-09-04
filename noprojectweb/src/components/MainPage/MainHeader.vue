<template>
  <div class="container-fluid">
    <span class="brand">No Project System</span>
    <div class="dropdown-layout">
      <el-dropdown>
        <el-button>
          Dropdown List
          <el-icon class="el-icon--right">
            <arrow-down/>
          </el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>Action 1</el-dropdown-item>
            <el-dropdown-item>Action 2</el-dropdown-item>
            <el-dropdown-item>Action 3</el-dropdown-item>
            <el-dropdown-item>Action 4</el-dropdown-item>
            <el-dropdown-item>Action 5</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import {ArrowDown} from '@element-plus/icons-vue'

export default {
  name: "MainHeader",
  components: {ArrowDown}
}
</script>

<style scoped>
.brand {
  float: left;
  display: block;
  padding: 8px 20px 12px;
  margin-left: -20px;
  font-size: 25px;
  font-weight: 200;
  color: #ffff;
}
.dropdown-layout {
  float: right;
  padding: 8px 20px 12px;
}
</style>