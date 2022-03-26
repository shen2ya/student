import Vue from 'vue'
import Router from 'vue-router'
import routes from './routers'
import store from '@/store'
import iView from 'iview'
import Cookies from 'js-cookie'
import { setToken, canTurnTo, setTitle } from '@/libs/util'
import config from '@/config'
const { homeName } = config

Vue.use(Router)
const router = new Router({
  routes,
  mode: 'history'
})
const LOGIN_PAGE_NAME = 'login'

const turnTo = (to, access, next) => {
  if (canTurnTo(to.name, access, routes)) next() // 有权限，可访问
  else next({ replace: true, name: 'error_401' }) // 无权限，重定向到401页面
}

router.beforeEach((to, from, next) => {
  iView.LoadingBar.start()
  const token = Cookies.get('id')
  if(to.name == 'video'){
    next()
    return
  }
  if (!token && to.name !== LOGIN_PAGE_NAME) {
    next({
      name: LOGIN_PAGE_NAME // 跳转到Login页
    })
  } else if (!token && to.name === LOGIN_PAGE_NAME) {
    // 未登陆且要跳转的页面是Login页
    next() // 跳转
  } else if (token && to.name === LOGIN_PAGE_NAME) {
    // 已Login且要跳转的页面是Login页
    next({
      name: homeName // 跳转到homeName页
    })
  } else {
    if (token) {
      turnTo(to, 1, next)
    } else {
      next({
        name: LOGIN_PAGE_NAME // 跳转到Login页
      })
    }
  }
})

router.afterEach(to => {
  setTitle(to, router.app)
  iView.LoadingBar.finish()
  window.scrollTo(0, 0)
})

export default router
