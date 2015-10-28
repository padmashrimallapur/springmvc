package com.respositories.jpa;

import com.entities.Account;
import com.entities.Blog;
import com.respositories.AccountRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    //any DAO or repository need to access the persistence resource, Depending on the persistence technology used.
    // The best way to accomplish this is by injecting the @Autowired, @Inject , @Resource, @Persistence.
    private EntityManager em;

    @Override
    public Account findAcount(Long id) {

        return em.find(Account.class, id);
    }

    @Override
    public Account creatAccount(Account data) {
        em.persist(data);
        return data;
    }

    @Override
    public Blog createBlog(Long id, Blog data) {

        return null;
    }

    @Override
    public Account findAccountByName(String name) {
        Query query = em.createQuery("SELECT a Account from a WHERE a.name=?1");
        query.setParameter(1, name);
        List<Account> accounts = query.getResultList();
        if (accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        Query query = em.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }

}
