<template>
  <Form ref="loginForm" :model="form" :rules="rules" @keydown.enter.native="handleSubmit">
    <FormItem prop="account">
      <Input v-model="form.name" placeholder="input phone">
        <span slot="prepend">
          <Icon :size="16" type="ios-person"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem prop="pwd">
      <Input type="password" v-model="form.pwd" placeholder="input password">
        <span slot="prepend">
          <Icon :size="14" type="md-lock"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem>
      <Button @click="handleSubmit" type="primary" long>Login</Button>
    </FormItem>
  </Form>
</template>
<script>
import Cookies from 'js-cookie'
export default {
  name: 'LoginForm',
  props: {
    userNameRules: {
      type: Array,
      default: () => {
        return [
          { required: true, message: 'phone is required', trigger: 'blur' }
        ]
      }
    },
    passwordRules: {
      type: Array,
      default: () => {
        return [
          { required: true, message: 'password is required', trigger: 'blur' }
        ]
      }
    }
  },
  data () {
    return {
      form: {
        name: '',
        pwd: ''
      }
    }
  },
  computed: {
    rules () {
      return {
        uPhone: this.userNameRules,
        uPwd: this.passwordRules
      }
    }
  },
  methods: {
    handleSubmit () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.$ajax.post('user/login', this.form).then(response => {
            let result = response.data
            if (result.success) {
              console.log(result.user)
              this.$Message.success('LoginSuccess')
              Cookies.set('id', result.user.id)
              Cookies.set('access', result.user.access)
              Cookies.set('name', result.user.name)
              this.$router.push({
                name: this.$config.homeName
              })
            } else {
              this.$Message.error(result.message)
            }
          }).catch(error => {
            this.$Message.error('Something Error：' + error)
          })
        }
      })
    }
  }
}
</script>
