<template>
  <div>
    <Form :model="formSearch" :label-width="80">
      <Row>
        <Col span="4">
          <FormItem label="Building">
            <Input v-model="formSearch.name" placeholder="input building"></Input>
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
          <FormItem label="Building">
            <Input v-model="formAdd.name" placeholder="input building"/>
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
          title: 'Building',
          align: 'center',
          key: 'name'
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
              editBtn,  validBtn
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
      this.$ajax.post('build/getList', this.formSearch).then(response => {
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
      this.$ajax.post('build/update', param).then(response => {
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
      this.$ajax.post('build/add', this.formAdd).then(response => {
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
    init(){
      this.search(1)
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
