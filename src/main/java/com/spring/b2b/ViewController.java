package com.spring.b2b;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ViewController extends BaseController {

	/*public HttpSession getSession() {
		HttpSession session = null;
		try {
		    session = getRequest().getSession(); 
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return session;
	}
	
	public HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder 
				.getRequestAttributes();
		return attrs.getRequest();
	}*/
    /*protected $allowAction = [];

    protected $loginInfo = [];

    public function __construct()
    {
        $this->getParameter();

        if (Session::has('login_info'))
        {
            $this->loginInfo = Session::get('login_info');
        }

        if(Request::path() === '/')
        {
            echo '<script>location.href="http://'.Request::server('HTTP_HOST').'/Index/index";</script>';
            exit;
        } else if (!$this->isSecurityIp && !in_array(Request::path(), $this->allowAction) && COUNT($this->loginInfo) === 0)
        {
            echo '<script>location.href="http://'.Request::server('HTTP_HOST').'/Public/login";</script>';
            exit;
        }
        parent::__construct();
    }

    protected function display($template)
    {
        View::share('args', $this->_request);
        //View::share('env', $this->getConfig());
        return view($template, $this->getConfig());
    }*/
}
