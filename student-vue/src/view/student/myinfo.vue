<style lang="less">
  @import './own-space.less';
</style>

<template>
    <div>
        <Card>
            <p slot="title">
                <Icon type="person"></Icon>
                个人信息
            </p>
            <div>
                <Form 
                    ref="userForm"
                    :model="userForm" 
                    :label-width="100" 
                    label-position="right"
                    :rules="inforValidate"
                >
                    <FormItem label="手机" prop="uPhone">
                        <div style="display:inline-block;width:300px;">
                            <Input :disabled="true" v-model="userForm.uPhone" ></Input>
                        </div>
                    </FormItem>
                    <FormItem label="姓名：" prop="uName">
                        <div style="display:inline-block;width:300px;">
                            <Input v-model="userForm.uName" ></Input>
                        </div>
                    </FormItem>
                    <FormItem label="工号：" prop="uNo" >
                        <div style="display:inline-block;width:300px;">
                            <Input v-model="userForm.uNo"></Input>
                        </div>
                    </FormItem>
                    <FormItem label="所属公司" prop="uPhone">
                        <div style="display:inline-block;width:300px;">
                            <Input :disabled="true" v-model="userForm.company" ></Input>
                        </div>
                    </FormItem>
                    <FormItem label="部门" prop="uPhone">
                        <div style="display:inline-block;width:300px;">
                            <Input :disabled="true" v-model="userForm.department" ></Input>
                        </div>
                    </FormItem>
                    <FormItem label="Login密码：">
                        <Button type="text" size="small" @click="showEditPassword">修改密码</Button>
                    </FormItem>
                    <div>
                        <Button type="text" style="width: 100px;" @click="cancelEditUserInfor">取消</Button>
                        <Button type="primary" style="width: 100px;" :loading="save_loading" @click="saveEdit">保存</Button>
                    </div>
                </Form>
            </div>
        </Card>
        <Modal v-model="editPasswordModal" :closable='false' :mask-closable=false :width="500">
            <h3 slot="header" style="color:#2D8CF0">修改密码</h3>
            <Form ref="editPasswordForm" :model="editPasswordForm" :label-width="100" label-position="right" :rules="passwordValidate">
                <FormItem label="原密码" prop="oldPass" :error="oldPassError">
                    <Input type="password" v-model="editPasswordForm.oldPass" placeholder="请输入现在使用的密码" ></Input>
                </FormItem>
                <FormItem label="新密码" prop="newPass">
                    <Input type="password" v-model="editPasswordForm.newPass" placeholder="请输入新密码" ></Input>
                </FormItem>
                <FormItem label="确认新密码" prop="rePass">
                    <Input type="password" v-model="editPasswordForm.rePass" placeholder="请再次输入新密码" ></Input>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button type="text" @click="cancelEditPass">取消</Button>
                <Button type="primary" :loading="savePassLoading" @click="saveEditPass">保存</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
    name: 'ownspace_index',
    data () {
        const valideRePassword = (rule, value, callback) => {
            if (value !== this.editPasswordForm.newPass) {
                callback(new Error('两次输入密码不一致'));
            } else {
                callback();
            }
        };
        return {
            userForm: {
            },
            save_loading: false,
            editPasswordModal: false, // 修改密码模态框显示
            savePassLoading: false,
            oldPassError: '',
            inforValidate: {
                name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' }
                ]
            },
            editPasswordForm: {
                oldPass: '',
                newPass: '',
                rePass: ''
            },
            passwordValidate: {
                oldPass: [
                    { required: true, message: '请输入原密码', trigger: 'blur' }
                ],
                newPass: [
                    { required: true, message: '请输入新密码', trigger: 'blur' },
                    { min: 1, message: '请至少输入1个字符', trigger: 'blur' },
                    { max: 32, message: '最多输入32个字符', trigger: 'blur' }
                ],
                rePass: [
                    { required: true, message: '请再次输入新密码', trigger: 'blur' },
                    { validator: valideRePassword, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        showEditPassword () {
            this.editPasswordModal = true;
        },
        cancelEditUserInfor () {
            this.init();
        },
        saveEdit () {
            this.$refs['userForm'].validate((valid) => {
                if (valid) {
                  this.save_loading = true;
                  this.$ajax.post('user/update', this.userForm).then(response => {
                    let data = response.data
                    if (data.success) {
                      this.$Message.success('Update Success')
                      this.init()
                    } else {
                      this.$Message.error('Update Error')
                    }
                    this.save_loading = false;
                  }).catch(e => {
                    this.$Message.error('Something Error' + e.message)
                    this.save_loading = false;
                  })
                }
            });
        },
        cancelEditPass () {
            this.editPasswordModal = false;
        },
        saveEditPass () {
            this.$refs['editPasswordForm'].validate((valid) => {
                if (valid) {
                    if(this.userForm.uPwd == this.editPasswordForm.oldPass){
                      this.savePassLoading = true
                      this.userForm.uPwd = this.editPasswordForm.newPass
                      this.$ajax.post('user/update', this.userForm).then(response => {
                        let data = response.data
                        if (data.success) {
                            this.$Message.success('Update Success')
                            this.cancelEditPass()
                            this.init()
                        } else {
                            this.$Message.error('Update Error')
                        }
                        this.savePassLoading = false;
                      }).catch(e => {
                        this.$Message.error('Something Error' + e.message)
                        this.savePassLoading = false;
                      })
                    } else {
                      this.$Message.error('原密码不正确')
                    }
                }
            });
        },
        init () {
          let user = {'uId': Cookies.get('id')}
          this.$ajax.post('user/login', user).then(response => {
            let data = response.data
            console.log(data)
            if (data.success) {
              this.userForm = data.user
            } else {
              this.$Message.error('Search Error')
            }
          }).catch(e => {
            this.$Message.error('Something Error' + e.message)
          })
        }
    },
    mounted () {
        this.init();
    }
};
</script>
