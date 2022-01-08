package com.example.repository;

import com.example.domain.Specialty;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Set;

@Singleton
public class SpecialtyRepositoryImp  implements SpecialtyRepository{

    private final SqlSessionFactory sqlSessionFactory;

    public SpecialtyRepositoryImp(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private SpecialtyRepository getSpecialRepository(SqlSession sqlSession){
        return sqlSession.getMapper(SpecialtyRepository.class);
    }
    @Override
    public Collection<Specialty> findAll() throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return getSpecialRepository(sqlSession).findAll();
        }
    }

    @Override
    public Specialty findById(Long id) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return getSpecialRepository(sqlSession).findById(id);
        }
    }

    @Override
    public Specialty findByName(String name) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return getSpecialRepository(sqlSession).findByName(name);
        }
    }

    @Override
    public Long save(Specialty specialty) throws Exception {
        Long specialtyId;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
           specialtyId = getSpecialRepository(sqlSession).save(specialty);
           sqlSession.commit();
        }
        return specialtyId;
    }

    @Override
    public void deleteById(Long vetID) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            getSpecialRepository(sqlSession).deleteById(vetID);
            sqlSession.commit();
        }
    }

    @Override
    public Set<Specialty> findByVetId(Long vetId) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            return getSpecialRepository(sqlSession).findByVetId(vetId);
        }
    }
}
