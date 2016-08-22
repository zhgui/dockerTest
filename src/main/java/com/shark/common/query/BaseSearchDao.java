package com.shark.common.query;

import com.shark.common.entity.search.Searchable;
import org.springframework.data.domain.Page;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午4:51
 */
public interface BaseSearchDao {

    public ReportPage searchPage(SearchConditions csc);

    public Page searchPage(Searchable searchable);
}
