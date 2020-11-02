package ImageHoster.repository;

import ImageHoster.model.Comments;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import ImageHoster.model.Tag;
        import org.springframework.stereotype.Repository;

        import javax.persistence.*;

@Repository
public class CommentsRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comments createComment(Comments comments) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comments);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comments;
    }

    public Tag findComments(Image image) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Tag> typedQuery = em.createQuery("SELECT t from Comments t where t.image =:image", Tag.class).setParameter("images", image);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
