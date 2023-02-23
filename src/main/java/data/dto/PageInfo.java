package data.dto;

import data.dto.enums.Order;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PageInfo {

    private int currentPageNo;      //현재 페이지 번호
    private int totalPageSize;      //총 페이지 갯수
    private int pageListSize;       //페이지 당 출력할 리스트 갯수
    private String keyword;         //검색할 정보
    private Order order;

    public PageInfo(){
        this.currentPageNo = 1;
        this.totalPageSize = 10;
        this.pageListSize = 20;
        this.keyword = null;
        this.order = Order.DATE;
    }

    /*
        LIMIT == pageListSize
        OFFSET == (currentPageNo - 1) * pageListSize
     */
}
