package com.iu.s1.board.notice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.iu.s1.board.BoardRepository;

@Repository //생략 가능
@Mapper //interface이므로 추가해야함

public interface NoticeRepository extends BoardRepository {

}
