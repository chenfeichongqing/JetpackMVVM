package com.github.chenfeichongqing.mvvmlib.base.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.chenfeichongqing.mvvmlib.R
import com.github.chenfeichongqing.mvvmlib.base.network.manager.NetState
import com.github.chenfeichongqing.mvvmlib.base.network.manager.NetworkStateManager
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel
import com.github.chenfeichongqing.mvvmlib.ext.getVmClazz
import com.github.chenfeichongqing.mvvmlib.utilcode.util.BarUtils
import com.github.chenfeichongqing.mvvmlib.utilcode.util.ColorUtils
import com.github.chenfeichongqing.mvvmlib.view.SaasNavBar
import com.google.android.material.appbar.AppBarLayout

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: ViewModelActivity基类，把ViewModel注入进来了
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {

    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isDataBinding= false

    //导航栏背景
    protected var appBar: AppBarLayout? = null

    protected var navBar: SaasNavBar? = null

    protected var toolbar: Toolbar? = null

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int


    open fun showLoading(message: String = "请求网络中..."){}

    open fun dismissLoading(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isFullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        super.onCreate(savedInstanceState)
        if (!isDataBinding) {
            setContentView(layoutId())
        } else {
            initDataBind()
        }
        initstatusBar()
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer {
            onNetworkStateChanged(it)
        })
    }

    open fun initView(savedInstanceState: Bundle?){
        toolbar = findViewById(R.id.toolbar)
        navBar = findViewById(R.id.nav_bar)
        navBar?.setBackListener {
            finish()
        }
        navBar?.rightView?.setTextColor(ColorUtils.getColor(R.color.base_color_333333))
        setMainTitle("测试")
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    /**
     * 注册UI 事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observe(this, Observer {
            showLoading(it)
        })
        //关闭弹窗
        mViewModel.loadingChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }

    /**
     * 将非该Activity绑定的ViewModel添加 loading回调 防止出现请求时不显示 loading 弹窗bug
     * @param viewModels Array<out BaseViewModel>
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel){
        viewModels.forEach {viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observe(this, Observer {
                showLoading(it)
            })
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observe(this, Observer {
                dismissLoading()
            })
        }
    }

    fun userDataBinding(isDataBinding: Boolean) {
        this.isDataBinding = isDataBinding
    }

    /**
     * 供子类BaseVmDbActivity 初始化Databinding操作
     */
    open fun initDataBind() {}


    //是否状态栏字体黑色
    open fun isLight(): Boolean {
        return true
    }
    //状态栏颜色
    open fun statusColor():Int{
        return  ColorUtils.getColor(R.color.colorPrimary)

    }
    private fun initstatusBar(){
        BarUtils.setStatusBarLightMode(this, isLight())
        BarUtils.setStatusBarColor(this, statusColor())

    }

    //是否全屏
    open fun isFullScreen(): Boolean {
        return false
    }
    /**
     * 设置主标题
     *
     * @param title 标题字符串
     */
    protected fun setMainTitle(title: String?) {
        navBar?.setTitle(title)
    }

}