package managerDB;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.ChatParty;

/**
 * Session Bean implementation class ChatPartyManagerBean
 */
@Stateless
public class ChatPartyManagerBean implements ChatPartyManagerLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ChatPartyManagerBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addMessage(int idParty, int idUser, String message) {
		ChatParty msg = new ChatParty();
		msg.setIdParty(idParty);
		msg.setIdUser(idUser);
		msg.setMessage(message);
		Date d = new Date();
		msg.setDate(d);
		this.em.persist(msg);
		this.em.flush();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatParty> getAllMessages(int idParty) {
		List<ChatParty> lcp = new ArrayList<ChatParty>();
		
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("SELECT c from ChatParty c where idParty =:id");
		query.setParameter("id", idParty);

		// Recuperation du resultat
		try {
			lcp = query.getResultList();
			
		} catch (NoResultException e)  {
			System.out.println("erreur");
			e.printStackTrace();
			return null;
		}
		
		return lcp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatParty> getMessagesAfter(int idParty,int idMessage) {
		
		List<ChatParty> lcp = new ArrayList<ChatParty>();
		// Creation de la requete
				Query query = em.createQuery("SELECT c from ChatParty c where idMessage>:idM and idParty=:idP");
				query.setParameter("idM", idMessage);
				query.setParameter("idP", idParty);
	
				
				// Recuperation du resultat
				try {
					lcp = query.getResultList();
				} catch (NoResultException e)  {
					System.out.println("erreur");
					e.printStackTrace();
					return null;
				}
				
				return  lcp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatParty> getAllMessagesLimit(int idParty, int limit) {
		List<ChatParty> lcp = new ArrayList<ChatParty>();
		
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("SELECT c from ChatParty c where idParty =:id");
		query.setParameter("id", idParty);

		// Recuperation du resultat
		try {
			lcp = query.getResultList();
			int taille = lcp.size();
			if(taille>limit){
				int end = taille - limit;
				for(int i =0; i < end;i++){
					//test si problème suppr premier
					lcp.remove(0);
				}
			}
		} catch (NoResultException e)  {
			System.out.println("erreur");
			e.printStackTrace();
			return null;
		}
		
		return lcp;
	}

}
