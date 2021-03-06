package com.example.repository;

import com.example.domain.Specialty;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.Set;

public interface SpecialtyRepository {

    @Select("SELECT * FROM petclinic.specialties")
    Collection<Specialty> findAll() throws Exception;

    @Select("SELECT * FROM petclinic.specialties WHERE id = #{id}")
    Specialty findById(@Param("id") Long id) throws  Exception;

    @Select("SELECT * FROM petclinic.specialties  WHERE UPPER(name) = #{name}")
    Specialty findByName(@Param("name") String name) throws Exception;

    @Select({"INSERT INTO petclinic.specialties(id, name)" +
            " VALUES (COALESCE(#{id},(select nextval('petclinic.specialties_id_seq'))), #{name})" +
            " ON CONFLICT (id)" +
            " DO UPDATE SET name = #{name}" +
            " WHERE petclinic.specialties.id = #{id}" +
            " RETURNING id"})
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    Long save(Specialty specialty) throws Exception;

    @Delete("DELETE FROM petclinic.specialties WHERE id = #{id}")
    void deleteById(@Param("vetId") Long vetID) throws Exception;

    @Select({" SELECT DISTINCT id, name FROM petclinic.specialties WHERE id IN(" +
            " SELECT specialty_id FROM petclinic.vet_specialties WHERE vet_id = #{vetId})"})
    Set<Specialty> findByVetId(@Param("vetId") Long vetId) throws Exception;

}
