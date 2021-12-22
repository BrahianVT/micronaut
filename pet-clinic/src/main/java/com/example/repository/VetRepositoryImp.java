package com.example.repository;


import com.example.domain.Vet;
import jakarta.inject.Singleton;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collection;

@Singleton
public class VetRepositoryImp  implements VetRepository {

    private final SqlSessionFactory sqlSessionFactory;

    public VetRepositoryImp(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private VetRepository getVetRepository(SqlSession sqlSession){
        return sqlSession.getMapper(VetRepository.class);
    }

    @Override
    public Collection<Vet> findAll() throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return getVetRepository(sqlSession).findAll();
        }
    }

    @Override
    public Vet findById(Long id) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return getVetRepository(sqlSession).findById(id);
        }
    }

    @Override
    public Long save(Vet vet) throws Exception {
        Long vetId;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            vetId = getVetRepository(sqlSession).save(vet);
            sqlSession.commit();
        }
        return vetId;
    }

    @Override
    public void saveVetSpecialty(Long vetID, Long specialtyId) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            getVetRepository(sqlSession).saveVetSpecialty(vetID, specialtyId);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            getVetRepository(sqlSession).deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteVetSpecialtyById(Long vetId) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            getVetRepository(sqlSession).deleteVetSpecialtyById(vetId);
            sqlSession.commit();
        }
    }

    @Override
    public void updateVetAverageRating(Long id, Double averageRating, Long ratingCount) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            getVetRepository(sqlSession).updateVetAverageRating(id, averageRating, ratingCount);
            sqlSession.commit();
        }
    }
}
