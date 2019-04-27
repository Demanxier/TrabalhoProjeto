package br.com.trabalho.controller;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.trabalho.mbeans.MbLogin;

/**
 * Reposavel por manipular requisiçÕes de usuario, permitindo acesso ao conteudo da 
 * aplicação somente no caso do usuario Já ter se autenticado
 * @author Demanxier
 *
 */
public class ListenerAutentication implements PhaseListener {
	
	private static final String PAGINA_LOGIN = "index.xhtml";
	
	@Override
	public void afterPhase(PhaseEvent event){
		
		FacesContext contexto = event.getFacesContext();
		String pagina = contexto.getViewRoot().getViewId();
		
		System.out.println("ListenerAutenticacao.afterPhase ()" 
		+ "para pagina de ID " + event.getFacesContext().getViewRoot().getViewId());
		
		if(!(pagina.lastIndexOf(PAGINA_LOGIN) > -1)){
			HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
			Object usuario = sessao.getAttribute(MbLogin.USUARIO_SESSAO);
			
			if(usuario == null){
				NavigationHandler navHandler = contexto.getApplication().getNavigationHandler();
				navHandler.handleNavigation(contexto, null, MbLogin.SESSAO_INEXISTENTE);
			}
		}
	}
	
	@Override
	public void beforePhase(PhaseEvent event){
		if(event.getFacesContext().getViewRoot() != null){
			System.out.println("ListenerAutenticacao.beforePhase() " 
					+ "para página de ID " + event.getFacesContext().getViewRoot().getViewId()); 
		}else{
			System.out.println("ListenerAutenticacao.beforePhase()  "
					+ "indicando view root ainda nula");
		}
	}
	
	@Override
	public PhaseId getPhaseId(){
		return PhaseId.RESTORE_VIEW;
	}
}