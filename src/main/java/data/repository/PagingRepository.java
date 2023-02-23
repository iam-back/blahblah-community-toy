package data.repository;

import data.dto.PageInfo;

import java.util.List;

public interface PagingRepository<T> {

    List<T> getListByPageInfo(PageInfo pageInfo);

}
