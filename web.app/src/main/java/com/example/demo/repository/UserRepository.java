package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

import jakarta.persistence.LockModeType;

/**
 * ユーザーデータを扱うリポジトリクラスです。
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 指定されたIDに該当するユーザー情報をPessimistic Lockで取得します。
	 *
	 * @param id 取得するユーザー情報のID
	 * @return 指定されたIDに該当するユーザー情報
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT u FROM User u WHERE u.id = :id")
	User findOneForUpdate(@Param("id") Long id);

	
//	追加機能
    /**
     * 指定されたキーワードを含むユーザー情報を検索します。
     *
     * @param keyword 検索キーワード
     * @return 検索結果のユーザー情報リスト
     */
    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% OR u.address LIKE %:keyword% OR u.phone LIKE %:keyword%")
    List<User> findByKeyword(@Param("keyword") String keyword);
	
	

}