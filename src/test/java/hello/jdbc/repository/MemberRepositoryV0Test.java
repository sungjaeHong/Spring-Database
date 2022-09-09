package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by peter on 2022/07/16
 */
@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();
    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV5", 10000);
        repositoryV0.save(member);

        // findById
        Member findMember = repositoryV0.findById(member.getMemberId());
        log.info("find member={}", findMember);
        Assertions.assertThat(findMember).isEqualTo(member);

        // update money : 10,000 -> 20,000
        repositoryV0.update(member.getMemberId(), 20000);
        Member updatedMember = repositoryV0.findById(member.getMemberId());
        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);
    }
}