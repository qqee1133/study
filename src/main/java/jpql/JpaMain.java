package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m where m.type = jpql.MemberType.ADMIN")
                    .getResultList();

            for (Object[] objects : resultList) {
                System.out.println("objects = " + objects[0]);
                System.out.println("objects = " + objects[1]);
            }


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
