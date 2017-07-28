/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4projec;

import java.io.Serializable;
import java.util.List;
import javafxapplication4projec.exceptions.NonexistentEntityException;
import javafxapplication4projec.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author opic
 */
public class DatasiswaJpaController implements Serializable {

    public DatasiswaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datasiswa datasiswa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datasiswa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatasiswa(datasiswa.getNis()) != null) {
                throw new PreexistingEntityException("Datasiswa " + datasiswa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datasiswa datasiswa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datasiswa = em.merge(datasiswa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datasiswa.getNis();
                if (findDatasiswa(id) == null) {
                    throw new NonexistentEntityException("The datasiswa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datasiswa datasiswa;
            try {
                datasiswa = em.getReference(Datasiswa.class, id);
                datasiswa.getNis();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datasiswa with id " + id + " no longer exists.", enfe);
            }
            em.remove(datasiswa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datasiswa> findDatasiswaEntities() {
        return findDatasiswaEntities(true, -1, -1);
    }

    public List<Datasiswa> findDatasiswaEntities(int maxResults, int firstResult) {
        return findDatasiswaEntities(false, maxResults, firstResult);
    }

    private List<Datasiswa> findDatasiswaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Datasiswa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Datasiswa findDatasiswa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datasiswa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatasiswaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Datasiswa> rt = cq.from(Datasiswa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
