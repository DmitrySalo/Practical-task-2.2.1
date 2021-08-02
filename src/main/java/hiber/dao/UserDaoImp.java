package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select user from User user", User.class)
                .getResultList();
    }

    @Override
    public User getCar(String model, int series) {
        return (User) sessionFactory
                .getCurrentSession()
                .createQuery("select user from Car where(model = :model) and (series = :series)")
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }
}