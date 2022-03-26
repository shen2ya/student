<template>
  <div>
    <Form :model="formSearch" :label-width="80">
      <Row>
        <Col span="4">
          <FormItem label="Student Name">
            <Input v-model="formSearch.name" placeholder="input student name"></Input>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="Student No">
            <Input v-model="formSearch.no" placeholder="input student no"></Input>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="Building">
            <Select v-model="formSearch.buildId" placeholder="select building" clearable>
              <Option v-for="item in buildList" :value="item.id">{{item.name}}</Option>
            </Select>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="Class">
            <Select v-model="formSearch.classId" placeholder="select class" clearable>
              <Option v-for="item in classList" :value="item.id">{{item.name}}</Option>
            </Select>
          </FormItem>
        </Col>
        <Col span="6">
          <Button type='primary' style="margin:0 20px;" @click="search(1)">Search</Button>
        </Col>
      </Row>
    </Form>
    <Table :loading="loading" style="margin-top:20px;" :data="data" ref="tables" editable :columns="columns"></Table>
    <Page :total="formSearch.total" :page-size="20" :current="formSearch.index" @on-change="search" style="margin-top:10px;float:right;"/>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  name: 'project',
  data () {
    return {
      access: Cookies.get('access'),
      id: Cookies.get('id'),
      loading: false,
      data: [],
      formSearch: {
        name: "",
        warn: 1
      },
      formAdd: {},
      addFlag: false,
      buildList:[],
      classList:[],
      studentList: [],
      roomList:[],
      roomFlag: false,
      columns: [
        {
          title: 'No',
          align: 'center',
          key: 'id'
        },
        {
          title: 'Student Name',
          align: 'center',
          key: 'StudentName'
        },
        {
          title: 'Student No',
          align: 'center',
          key: 'no'
        },
        {
          title: 'Class',
          align: 'center',
          key: 'ClassName'
        },
        {
          title: 'Building',
          align: 'center',
          key: 'buildName'
        },
        {
          title: 'Dorm',
          align: 'center',
          key: 'roomName'
        },
        {
          title: 'Sign Time',
          align: 'center',
          key: 'sign_time',
          render: (h, params) => {
            return h('span', params.row.sign_time ? this.formartDate(new Date(params.row.sign_time)) : '')
          }
        },
        
        {
          title: 'Sign Type',
          align: 'center',
          key: 'valid',
          render: (h, params) => {
            return h('span', params.row.sign_type == 1 ? 'Out' : 'In')
          }
        },
        {
          title: 'Notes',
          align: 'center',
          key: 'notes'
        },
        {
          title: 'Warning',
          align: 'center',
          key: 'notes',
          render: (h, params) => {
            return h('span', {attrs: {style: "color: red"}},'Later Than 10 Clock')
          }
        }
      ]
    }
  },
  methods: {
    search (index) {
      this.loading = true
      this.formSearch.index = index
      this.$ajax.post('studentSign/getList', this.formSearch).then(response => {
        let data = response.data
        if (data.success) {
          let pagedata = data.page
          this.data = pagedata.list
          this.formSearch.total = pagedata.total
        } else {
          this.$Message.error('Search Error')
        }
        this.loading = false
      }).catch(e => {
        this.loading = false
        this.$Message.error('Something Error' + e.message)
      })
    },
    doUpdate (param) {
      this.$ajax.post('studentSign/update', param).then(response => {
        let data = response.data
        if (data.success) {
          this.$Message.success('Update Success')
          this.addFlag = false
          this.roomFlag = false
          this.search(1)
        } else {
          this.$Message.error('Update Error')
        }
      }).catch(e => {
        this.$Message.error('Something Error' + e.message)
      })
    },
    add() {
      this.formAdd = { 
        access: '3',
        valid: 1
      }
      this.addFlag = true
    },
    doAdd () {
      if(this.formAdd.id){
        this.doUpdate(this.formAdd)
        return
      }
      this.$ajax.post('studentSign/add', this.formAdd).then(response => {
        let data = response.data
        if (data.success) {
          this.$Message.success('Add Success')
          this.addFlag = false
          this.search(1)
        } else {
          this.$Message.error(data.message)
        }
      }).catch(e => {
        this.$Message.error('Something Error' + e.message)
      })
    },
    getRoom(value){
      this.$ajax.post('buildRoom/getList', {buildId: value}).then(response => {
        let data = response.data
        this.roomList = data.page
      }).catch(e => {
        this.$Message.error('Something Error' + e.message)
      })
    },
    getStudent(value){
      this.$ajax.post('student/getList', {name: value, index: 1}).then(response => {
        let data = response.data
        this.studentList = data.page.list
      }).catch(e => {
        this.$Message.error('Something Error' + e.message)
      })
    },
    formartDate(time, pattern){
      if (arguments.length === 0 || !time) {
        return null
      }
      const format = pattern || '{y}-{m}-{d} {h}:{i}:{s}'
      let date
      if (typeof time === 'object') {
        date = time
      } else {
        if ((typeof time === 'string') && (/^[0-9]+$/.test(time))) {
          time = parseInt(time)
        } else if (typeof time === 'string') {
          time = time.replace(new RegExp(/-/gm), '/').replace('T', ' ').replace(new RegExp(/\.[\d]{3}/gm),'');
        }
        if ((typeof time === 'number') && (time.toString().length === 10)) {
          time = time * 1000
        }
        date = new Date(time)
      }
      const formatObj = {
        y: date.getFullYear(),
        m: date.getMonth() + 1,
        d: date.getDate(),
        h: date.getHours(),
        i: date.getMinutes(),
        s: date.getSeconds(),
        a: date.getDay()
      }
      const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
        let value = formatObj[key]
        // Note: getDay() returns 0 on Sunday
        if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value] }
        if (result.length > 0 && value < 10) {
          value = '0' + value
        }
        return value || 0
      })
      return time_str
    },
    init(){
      this.formSearch.access = this.access
      this.formSearch.userId = this.id
      this.search(1)
      this.$ajax.post('class/getList', {}).then(response => {
        let data = response.data
        this.classList = data.page
      }).catch(e => {
        this.$Message.error('Something Error' + e.message)
      })
      this.$ajax.post('build/getList', {}).then(response => {
        let data = response.data
        this.buildList = data.page
      }).catch(e => {
        this.$Message.error('Something Error' + e.message)
      })
    }
  },
  mounted () {
    this.init()
  }
}
</script>
<style>
.top *{
  z-index: 100001;
}
</style>
