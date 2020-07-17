//package filter;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import model.ErrorViewData;
//import model.ValidationKey;
//
///**
// * Servlet Filter implementation class ValidationKeyCheck
// */
//@WebFilter("")
//public class ValidationKeyCheck implements Filter {
//
//    /**
//     * Default constructor. 
//     */
//    public ValidationKeyCheck() {
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		
//		HttpSession session = ((HttpServletRequest) request).getSession();
//		// フォームから送られた確認キーが保存したものと一致するか確認
//		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
//		if (!request.getParameter("vKey").equals(validationKey.getValue())) {
//			 // 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
//			session.removeAttribute("validationKey");
//			//表示データを用意する
//			ErrorViewData errorData = new ErrorViewData("問題が発生しました。","トップに戻る","/ActionLogger/Main");
//			request.setAttribute("errorData", errorData);
//			//エラー表示にフォワード
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
//			dispatcher.forward(request, response);
//			return;
//		}
//		chain.doFilter(request, response);
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//}
