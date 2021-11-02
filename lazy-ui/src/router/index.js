import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import webLayout from '@/views/web/webLayout'

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    noCache: true                // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
    activeMenu: '/system/user'   // 当路由设置了该属性，则会高亮相对应的侧边栏。
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: (resolve) => require(['@/views/redirect'], resolve)
      }
    ]
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/index'], resolve),
        name: '首页',
        meta: {title: '首页', icon: 'dashboard', noCache: true, affix: true}
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
        name: 'Profile',
        meta: {title: '个人中心', icon: 'user'}
      }
    ]
  },
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: (resolve) => require(['@/views/system/user/authRole'], resolve),
        name: 'AuthRole',
        meta: {title: '分配角色', activeMenu: '/system/user'}
      }
    ]
  },
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: (resolve) => require(['@/views/system/role/authUser'], resolve),
        name: 'AuthUser',
        meta: {title: '分配用户', activeMenu: '/system/role'}
      }
    ]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index/:dictId(\\d+)',
        component: (resolve) => require(['@/views/system/dict/data'], resolve),
        name: 'Data',
        meta: {title: '字典数据', activeMenu: '/system/dict'}
      }
    ]
  },
  {
    path: '/vm/question/form',
    component: Layout,
    hidden: true,
    children: [
      {
        path: ':id?',
        component: (resolve) => require(['@/views/vm/question/form'], resolve),
        name: 'quForm',
        meta: {title: '添加题库', activeMenu: '/vm/question'}
      }
    ]
  },
  {
    path: '/vm/question/testQuestions',
    component: Layout,
    hidden: true,
    children: [
      {
        path: ':id?',
        component: (resolve) => require(['@/views/vm/question/TestQuestions'], resolve),
        name: 'quManage',
        meta: {title: '试题管理', activeMenu: '/vm/question'}
      }
    ]
  },
  {
    path: '/vm/question/testQuestionForm',
    component: Layout,
    hidden: true,
    children: [
      {
        path: ':id?',
        component: (resolve) => require(['@/views/vm/question/TestQuestionForm'], resolve),
        name: 'quAdd',
        meta: {title: '添加试题', activeMenu: '/vm/question'}
      }
    ]
  },
  {
    path: '/vm/testPaper/paperForm',
    component: Layout,
    hidden: true,
    children: [
      {
        path: ':id?',
        component: (resolve) => require(['@/views/vm/testPaper/paperForm'], resolve),
        name: 'paperAdd',
        meta: {title: '添加试卷', activeMenu: '/vm/testPaper'}
      }
    ]
  },
  {
    path: '/vm/testPaper/paperForm',
    component: Layout,
    hidden: true,
    children: [
      {
        path: ':id?',
        component: (resolve) => require(['@/views/vm/testPaper/paperForm'], resolve),
        name: 'paperUpdate',
        meta: {title: '修改试卷', activeMenu: '/vm/testPaper'}
      }
    ]
  },
  {
    path: '/vm/exam/form',
    component: Layout,
    hidden: true,
    children: [
      {
        path: ':id?',
        component: (resolve) => require(['@/views/vm/exam/form'], resolve),
        name: 'examAdd',
        meta: {title: '添加考试', activeMenu: '/vm/exam'}
      }
    ]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/monitor/job/log'], resolve),
        name: 'JobLog',
        meta: {title: '调度日志', activeMenu: '/monitor/job'}
      }
    ]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index/:tableId(\\d+)',
        component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
        name: 'GenEdit',
        meta: {title: '修改生成配置', activeMenu: '/tool/gen'}
      }
    ]
  },
  {
    path: '/web/exam/start',
    component: (resolve) => require(['@/views/web/exam/start'], resolve),
    hidden: true
  },
  {
    path: '/web/exam/check',
    component: webLayout,
    hidden: true,
    children: [
      {
        path: ':id?',
        component: (resolve) => require(['@/views/web/exam/check'], resolve),
        name: 'check',
        meta: {title: '检查'}
      }
    ]
  },
  {
    path: '/web/index',
    component: webLayout,
    hidden: true,
    children: [
      {
        path: ':id?',
        component: (resolve) => require(['@/views/web/exam/List'], resolve),
        name: 'onlineList',
        meta: {title: '作业列表'}
      }
    ]
  },
  {
    path: '/web/vm/liShuToSatellitePosition',
    component: (resolve) => require(['@/views/web/vm/LiShuToSatellitePosition'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/TimeTransition',
    component: (resolve) => require(['@/views/web/vm/TimeTransition'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/BeijingToUTC',
    component: (resolve) => require(['@/views/web/vm/BeijingToUTC'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/dxdgzjzbx',
    component: (resolve) => require(['@/views/web/vm/GeocenGeostatiRectan'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/dongbei',
    component: (resolve) => require(['@/views/web/vm/dongbei'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/GSM',
    component: (resolve) => require(['@/views/web/vm/GSM'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/Navigation',
    component: (resolve) => require(['@/views/web/vm/Navigation'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/GPS',
    component: (resolve) => require(['@/views/web/vm/GPS'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/BDS',
    component: (resolve) => require(['@/views/web/vm/BDS'], resolve),
    hidden: true
  },
  {
    path: '/web/vm/xingliTOweixing',
    component: (resolve) => require(['@/views/web/vm/xingliTOweixing'], resolve),
    hidden: true
  },
]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})
