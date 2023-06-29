package com.example.demo.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;

/**
 * ユーザー情報を扱サービスクラスです。
 */

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	/**
	 * 全てのユーザー情報を取得します。
	 *
	 * @return 全てのユーザー情報
	 */
	public List<User> searchAll() {
		return userRepository.findAll();
	}
	
	/**
	 * 指定されたユーザーIDに対応するユーザー情報を取得します。
	 *
	 * @param id ユーザーID
	 * @return 指定されたユーザーIDに対応するユーザー情報
	 */
	public User search(Long id) {
		return userRepository.findById(id).get();
	}
	
	/**
	 * 指定されたユーザー情報を登録します。
	 *
	 * @param user 登録するユーザー情報
	 * @return 登録されたユーザー情報
	 */
	public User createUser(User user) {
		LocalDateTime now = LocalDateTime.now();
		user.setCreateDate(now);
		user.setUpdateDate(now);
		return userRepository.save(user);
	}
	
	/**
	 * 指定されたユーザー情報を登録します。
	 *
	 * @param user 登録するユーザー情報
	 * @return 登録されたユーザー情報
	 */
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	/**
	 * 指定されたユーザー情報を更新します。
	 * <p>
	 * 更新時に排他制御（オプティミスティックロック）を行います。
	 * </p>
	 *
	 * @param user 更新するユーザー情報
	 * @return 更新されたユーザー情報
	 * @throws OptimisticLockException 他のユーザーによってデータが更新されている場合にスローされます。
	 */
	@Transactional
	public User updateUser(User user) {
		
		User currentUser = userRepository.findOneForUpdate(user.getId());

		if (currentUser.getUpdateDate().equals(user.getUpdateDate())) {
			LocalDateTime now = LocalDateTime.now();
			user.setUpdateDate(now);
			return userRepository.save(user);
		} else {
			String message = "データが他の方によって更新されたようです。一覧画面に戻ってから再実施してください。";
			throw new OptimisticLockException(message);
		}
	}
	
	
	//追加機能
	/**
	 * 指定されたキーワードを含むユーザーを検索する。
	 *
	 * @param keyword 検索キーワード
	 * @return キーワードに一致するユーザーのリスト
	 */
	public List<User> searchUsersByKeyword(String keyword) {
	    return userRepository.findByKeyword(keyword);
	    // リポジトリのメソッドを呼び出してキーワードに一致するユーザーを検索するロジックを実装する
	    // 例: return userRepository.findByKeyword(keyword);
	}
}