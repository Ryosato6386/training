package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Bootアプリケーションのエントリーポイントとなるクラスです。
 * （エントリーポイントとは、
 * プログラムの実行が開始される場所、関数、メソッド、クラス、ファイルなどのこと）
 * 
 * <p>
 * このクラスは {@code @SpringBootApplication} アノテーションが付与されており、Spring
 * Bootアプリケーションのメインクラスとして機能します。
 * </p>
 */

@SpringBootApplication
public class Application {
	
	/**
	 * Spring Bootアプリケーションの起動
	 *
	 * @param args 起動のためのコマンドライン引数
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
