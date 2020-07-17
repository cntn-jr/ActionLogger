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
//		// �t�H�[�����瑗��ꂽ�m�F�L�[���ۑ��������̂ƈ�v���邩�m�F
//		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
//		if (!request.getParameter("vKey").equals(validationKey.getValue())) {
//			 // ��v���Ȃ������̂ŁA�Z�b�V�����X�R�[�v�ɕۑ������L�[��j�����A�G���[�y�[�W��
//			session.removeAttribute("validationKey");
//			//�\���f�[�^��p�ӂ���
//			ErrorViewData errorData = new ErrorViewData("��肪�������܂����B","�g�b�v�ɖ߂�","/ActionLogger/Main");
//			request.setAttribute("errorData", errorData);
//			//�G���[�\���Ƀt�H���[�h
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
