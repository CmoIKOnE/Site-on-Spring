package com.pzh.code.archyonix.repository.db1;

import com.pzh.code.archyonix.history.HistoryShopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryShoppingRepository extends JpaRepository<HistoryShopping, Long> {
}
