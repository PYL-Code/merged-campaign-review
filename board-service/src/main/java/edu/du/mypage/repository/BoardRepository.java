package edu.du.mypage.repository;

import edu.du.mypage.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
} 