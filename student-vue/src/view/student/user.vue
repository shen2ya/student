<template>
  <div>
    <Form :model="formSearch" :label-width="80">
      <Row>
        <Col span="6">
          <FormItem label="Username">
            <Input v-model="formSearch.name" placeholder="input username"></Input>
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
          <FormItem label="Username">
            <Input v-model="formAdd.name" placeholder="input username"/>
          </FormItem>
          <FormItem label="Phone">
            <Input v-model="formAdd.phone" placeholder="input phone"/>
          </FormItem>
          <FormItem label="Password">
            <Input v-model="formAdd.pwd" placeholder="input password"/>
          </FormItem>
          <FormItem label="Role">
            <Select v-model="formAdd.access" placeholder="select role">
              <Option :value="1">Houseparent</Option>
              <Option :value="2">Instructor</Option>
              <Option :value="3">School work division</Option>
            </Select>
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" shape="circle" size="large" long @click="doAdd">Save</Button>
      </div>
    </Modal>

    <Modal v-model="buildFlag" width="400">
      <p slot="header">
        <span>Building</span>
      </p>
      <div style="text-align:center;width:90%">
        <Form :model="formAdd" :label-width="90">
          <FormItem label="Building">
            <Select v-model="formAdd.buildId" placeholder="select building" multiple>
              <Option v-for="item in buildList" :value="item.id">{{item.name}}</Option>
            </Select>
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" shape="circle" size="large" long @click="updateBuild">Save</Button>
      </div>
    </Modal>
    
    <Modal v-model="classFlag" width="400">
      <p slot="header">
        <span>Class</span>
      </p>
      <div style="text-align:center;width:90%">
        <Form :model="formAdd" :label-width="90">
          <FormItem label="Class">
            <Select v-model="formAdd.classId" placeholder="select class" multiple>
              <Option v-for="item in classList" :value="item.id">{{item.name}}</Option>
            </Select>
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" shape="circle" size="large" long @click="updateClass">Save</Button>
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
      loading: false,
      data: [],
      formSearch: {
        name: ""
      },
      formAdd: {},
      addFlag: false,
      buildList:[],
      classList:[],
      buildFlag: false,
      classFlag: false,
      columns: [
        {
          title: 'No',
          align: 'center',
          key: 'id'
        },
        {
          title: 'Username',
          align: 'center',
          key: 'name'
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
            return h('span', params.row.valid == 1 ? 'Valid' : 'Invalid')
          }
        },
        {
          title: 'Role',
          align: 'center',
          key: 'admin',
          render: (h, params) => {
            return h('span', params.row.access == 1 ? 'Houseparent' : params.row.access == 2 ? 'Instructor' : 'School work division')
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
                      if(params.row.access == 1){
                         this.$ajax.post('user/getUserBuild', this.formAdd).then(response => {
                          let data = response.data
                          if (data.success) {
                            this.formAdd.buildId = []
                            if(data.list){
                              data.list.forEach(item => {
                                this.formAdd.buildId.push(item.buildId)
                              })
                            }
                            
                            this.buildFlag = true
                          } else {
                            this.$Message.error(data.message)
                          }
                        }).catch(e => {
                          this.$Message.error('Something Error' + e.message)
                        })
                      } else {
                         this.$ajax.post('user/getUserClass', this.formAdd).then(response => {
                          let data = response.data
                          if (data.success) {
                            this.formAdd.classId = []
                            if(data.list){
                              data.list.forEach(item => {
                                this.formAdd.classId.push(item.classId)
                              })
                            }
                            this.classFlag = true
                          } else {
                            this.$Message.error(data.message)
                          }
                        }).catch(e => {
                          this.$Message.error('Something Error' + e.message)
                        })
                        
                      }
                    }
                  }
                }, 'Distribution')
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
                }, params.row.valid == 1 ? 'Invalid' : 'Valid')
            return h('div', [
              editBtn, params.row.access == 3 ? '' : setBtn, validBtn
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
      this.$ajax.post('user/getList', this.formSearch).then(response => {
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
      this.$ajax.post('user/update', param).then(response => {
        let data = response.data
        if (data.success) {
          this.$Message.success('Update Success')
          this.addFlag = false
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
      this.$ajax.post('user/add', this.formAdd).then(response => {
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
    updateBuild(){
      this.$ajax.post('user/updateUserBuild', this.formAdd).then(response => {
        this.buildFlag = false
        this.$Message.success('Success')
      }).catch(e => {
        this.$Message.error('Something Error' + e.message)
      })
    },
    updateClass(){
      this.$ajax.post('user/updateUserClass', this.formAdd).then(response => {
        this.classFlag = false
        this.$Message.success('Success')
      }).catch(e => {
        this.$Message.error('Something Error' + e.message)
      })
    },
    init(){
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
