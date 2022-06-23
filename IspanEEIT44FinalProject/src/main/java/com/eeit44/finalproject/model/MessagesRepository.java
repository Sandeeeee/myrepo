package com.eeit44.finalproject.model;

import java.io.File;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MessagesRepository extends JpaRepository<Messages, Integer> {

	
	@Query(value = "from Messages where senderId = :senderId")
	public List<Messages> findBySenderId(@Param(value = "senderId") int senderId);
	
	@Query(value = "select * from (select ROW_NUMBER() OVER (PARTITION BY receiverId ORDER BY created desc) as ROW_ID ,* from messages) as lastone where lastone.ROW_ID =1  order by created desc", nativeQuery=true)
	public List<Messages> queryLastMessage (@Param(value = "senderId") int senderId);
	
//	select * from messages where senderId=1001 and receiverId=3 or senderId=3 and receiverId=1001 order by created asc

//	@Query(value ="" ", nativeQuery=true)
//	public  File fileUpload (@Param(value = "senderId") int senderId);
//	
	
	@Modifying
	@Query(value = "delete from messages where receiverId = :receiverId", nativeQuery=true)
	public void deleteByReceiverId(@Param(value = "receiverId") int receiverId);

	
}
