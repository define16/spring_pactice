package com.web.domain.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"userNo", "itemId"})
public class UserItem {
    private int userNo;
    private int itemId;

    private String itemName;
    private Integer price;
    private String description;
// @EqualsAndHashCode 인해 생겨남
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + itemId;
//        result = prime * result + userNo;
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        UserItem other = (UserItem) obj;
//        if (itemId != other.itemId)
//            return false;
//        if (userNo != other.userNo)
//            return false;
//        return true;
//    }
}
