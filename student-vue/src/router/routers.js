import Main from '@/components/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  title: { String|Number|Function }
 *         显示在侧边栏、面包屑和标签栏的文字
 *         使用'{{ 多语言字段 }}'形式结合多语言使用，例子看多语言的路由配置;
 *         可以传入一个回调函数，参数是当前路由对象，例子看动态路由和带参路由
 *  hideInBread: (false) 设为true后此级路由将不会出现在面包屑中，示例看QQ群路由配置
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面在切换标签后不会缓存，如果需要缓存，无需设置这个字段，而且需要设置页面组件name属性和路由配置的name一致
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 *  beforeCloseName: (-) 设置该字段，则在关闭当前tab页时会去'@/router/before-close.js'里寻找该字段名对应的方法，作为关闭前的钩子函数
 * }
 */

export default [
  {
    path: '/video',
    name: 'video',
    meta: {
      title: 'Sign',
      hideInMenu: true
    },
    component: () => import('@/view/student/video.vue')
  },
  {
    path: '/login',
    name: 'login',
    meta: {
      title: 'Login',
      hideInMenu: true
    },
    component: () => import('@/view/login/login.vue')
  },
  {
    path: '/',
    name: '_home',
    redirect: '/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {
          hideInMenu: true,
          title: 'Home',
          notCache: true,
          icon: 'md-home'
        },
        component: () => import('@/view/single-page/home')
      }
    ]
  },
  {
    path: '/student',
    name: 'student',
    component: Main,
    meta: {
      hideInBread: true
    },
    children: [
      {
        path: 'book',
        name: 'Student Info Mgr',
        meta: {
          icon: 'ios-calendar',
          title: 'Student Info Mgr'
        },
        component: () => import('@/view/student/student.vue')
      }
    ]
  },
  {
    path: '/record',
    name: 'record',
    component: Main,
    meta: {
      hideInBread: true
    },
    children: [
      {
        path: 'record',
        name: 'Sign In Record Mgr',
        meta: {
          icon: 'ios-calendar',
          title: 'Sign In Record Mgr'
        },
        component: () => import('@/view/student/record.vue')
      }
    ]
  },
  // {
  //   path: '/buildlive',
  //   name: 'buildlive',
  //   component: Main,
  //   meta: {
  //     hideInBread: true,
  //     access: [1]
  //   },
  //   children: [
  //     {
  //       path: 'buildlive',
  //       name: '楼栋就寝信息Search',
  //       meta: {
  //         icon: 'ios-calendar',
  //         title: '楼栋学生就寝信息Search'
  //       },
  //       component: () => import('@/view/student/index.vue')
  //     }
  //   ]
  // },
  // {
  //   path: '/classlive',
  //   name: 'classlive',
  //   component: Main,
  //   meta: {
  //     hideInBread: true,
  //     access: [2]
  //   },
  //   children: [
  //     {
  //       path: 'classlive',
  //       name: '班级学生就寝信息Search',
  //       meta: {
  //         icon: 'ios-calendar',
  //         title: '班级学生就寝信息Search'
  //       },
  //       component: () => import('@/view/student/index.vue')
  //     }
  //   ]
  // },
  {
    path: '/warn',
    name: 'warn',
    component: Main,
    meta: {
      hideInBread: true,
      access: [1,2,3]
    },
    children: [
      {
        path: 'warn',
        name: 'Warning Mgr',
        meta: {
          icon: 'ios-calendar',
          title: 'Warning Mgr'
        },
        component: () => import('@/view/student/warn.vue')
      }
    ]
  },
  
  {
    path: '/class',
    name: 'class',
    component: Main,
    meta: {
      hideInBread: true,
      access: [3]
    },
    children: [
      {
        path: 'class',
        name: 'Class Mgr',
        meta: {
          icon: 'ios-calendar',
          title: 'Class Mgr'
        },
        component: () => import('@/view/student/class.vue')
      }
    ]
  },
  {
    path: '/buiild',
    name: 'buiild',
    component: Main,
    meta: {
      hideInBread: true,
      access: [3]
    },
    children: [
      {
        path: 'buiild',
        name: 'Building Mgr',
        meta: {
          icon: 'ios-calendar',
          title: 'Building Mgr'
        },
        component: () => import('@/view/student/build.vue')
      }
    ]
  },
  {
    path: '/room',
    name: 'room',
    component: Main,
    meta: {
      hideInBread: true,
      access: [3]
    },
    children: [
      {
        path: 'room',
        name: 'Room Mgr',
        meta: {
          icon: 'ios-calendar',
          title: 'Room Mgr'
        },
        component: () => import('@/view/student/room.vue')
      }
    ]
  },
  {
    path: '/user',
    name: 'user',
    component: Main,
    meta: {
      hideInBread: true,
      access: [3]
    },
    children: [
      {
        path: 'user',
        name: 'User Mgr',
        meta: {
          icon: 'ios-calendar',
          title: 'User Mgr'
        },
        component: () => import('@/view/student/user.vue')
      }
    ]
  }
  
]
