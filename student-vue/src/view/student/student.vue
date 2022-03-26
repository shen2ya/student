<template>
  <div>
    <Form :model="formSearch" :label-width="80">
      <Row>
        <Col span="4">
          <FormItem label="Student Name">
            <Input v-model="formSearch.name" placeholder="input name"></Input>
          </FormItem>
        </Col>
        <Col span="4">
          <FormItem label="Student No">
            <Input v-model="formSearch.no" placeholder="input no"></Input>
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
          <Button type='primary' style="margin:0 20px;" @click="add">Add</Button>
        </Col>
      </Row>
    </Form>
    <Table :loading="loading" style="margin-top:20px;" :data="data" ref="tables" editable :columns="columns"></Table>
    <Page :total="formSearch.total" :page-size="20" :current="formSearch.index" @on-change="search" style="margin-top:10px;float:right;"/>
    <Modal v-model="addFlag" width="400">
      <p slot="header">
        <span v-if="formAdd.id">Update</span>
        <span v-else>Add</span>
      </p>
      <div style="text-align:center;width:90%">
        <Form :model="formAdd" :label-width="90">
          <FormItem label="Student Name">
            <Input v-model="formAdd.name" placeholder="input student name"/>
          </FormItem>
          <FormItem label="Student No">
            <Input v-model="formAdd.no" placeholder="input student no"/>
          </FormItem>
          <FormItem label="Phone">
            <Input v-model="formAdd.phone" placeholder="input phone"/>
          </FormItem>
          <FormItem label="Sex">
            <Select v-model="formAdd.sex" placeholder="select sex">
              <Option :value="1">Man</Option>
              <Option :value="2">Woman</Option>
            </Select>
          </FormItem>
          <FormItem label="Class">
            <Select v-model="formAdd.studentClass" placeholder="select class">
              <Option v-for="item in classList" :value="item.id">{{item.name}}</Option>
            </Select>
          </FormItem>
          <FormItem label="Head Img">
            <img :src="'http://localhost:8800/files/' + formAdd.img" style="width: 100px; height: 100px;">
            <!-- :headers="header" -->
            <Upload name="file" action="http://localhost:8800/file/upload?pid=img" :on-success="fileSuccess" :show-upload-list="false">
              <Button icon="ios-cloud-upload-outline">Upload File</Button>
            </Upload>
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" shape="circle" size="large" long @click="doAdd">Save</Button>
      </div>
    </Modal>

    <Modal v-model="roomFlag" width="400">
      <p slot="header">
        <span>Class</span>
      </p>
      <div style="text-align:center;width:90%">
        <Form :model="formAdd" :label-width="90">
          <FormItem label="Building">
            <Select v-model="formAdd.studentBuild" placeholder="select building" @on-change="getRoom">
              <Option v-for="item in buildList" :value="item.id">{{item.name}}</Option>
            </Select>
          </FormItem>
          <FormItem label="Dorm">
            <Select v-model="formAdd.studentRoom" placeholder="select dorm">
              <Option v-for="item in roomList" :value="item.id">{{item.name}}</Option>
            </Select>
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" shape="circle" size="large" long @click="doAdd">Save</Button>
      </div>
    </Modal>
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
        name: ""
      },
      formAdd: {},
      addFlag: false,
      buildList:[],
      classList:[],
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
          key: 'name'
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
          title: 'Buinding',
          align: 'center',
          key: 'buildName'
        },
        {
          title: 'Dorm',
          align: 'center',
          key: 'roomName'
        },
        {
          title: 'Phone',
          align: 'center',
          key: 'phone'
        },
        
        {
          title: 'Status',
          align: 'center',
          key: 'valid',
          render: (h, params) => {
            return h('span', params.row.valid == 1 ? 'Studying' : 'Drop out')
          }
        },
        {
          title: 'Handel',
          key: 'date',
          width: 230,
          align: 'center',
          render: (h, params) => {
            let editBtn = h('Button',
                {
                  style: {
                    'margin-right': '10px'
                  },
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.formAdd = JSON.parse(JSON.stringify(params.row))
                      this.formAdd.studentClass = this.formAdd.student_class
                      this.addFlag = true
                    }
                  }
                }, 'Edit')
              let setBtn = h('Button',
                {
                  style: {
                    'margin-right': '10px'
                  },
                  props: {
                    type: 'warning',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.formAdd = JSON.parse(JSON.stringify(params.row))
                      this.formAdd.studentBuild = this.formAdd.student_build
                      this.formAdd.studentRoom = this.formAdd.student_room
                      if(this.formAdd.studentBuild) this.getRoom(this.formAdd.studentBuild)
                      this.roomFlag = true
                    }
                  }
                }, 'Dorm')
            let validBtn = h('Button',
                {
                  props: {
                    type: params.row.valid == 1 ? 'error' : 'success',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      let temp = params.row
                      temp.valid = temp.valid == 0 ? 1 : 0
                      this.doUpdate(temp)
                    }
                  }
                }, params.row.valid == 1 ? 'Drop out' : 'Studying')
            return h('div', [
              editBtn, setBtn,  validBtn
            ])
          }
        }
      ]
    }
  },
  methods: {
    search (index) {
      this.loading = true
      this.formSearch.index = index
      this.$ajax.post('student/getList', this.formSearch).then(response => {
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
      this.$ajax.post('student/update', param).then(response => {
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
      this.$ajax.post('student/add', this.formAdd).then(response => {
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
    fileSuccess(response, file, fileList) {
      if (response.success) {
        // this.uploadFile.fileName = response.tip.fileName;
        this.formAdd.img = response.tip.newName;
        this.$Message.info("upload success");
      } else {
        this.$Message.error("Something Error");
      }
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
