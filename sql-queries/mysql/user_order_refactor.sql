/**
  store_id를 사용해서 user_order_id가 몇 개 있는지 알 수 있는 방법이 없음
  그래서 store와 user_order_id가 1:N의 관계가 되도록 수정해주는 쿼리
 */

# 테이블 내용 초기화
truncate table delivery.user_order_id;
truncate table delivery.user_order_menu;

# user order id 테이블에 store_id 컬럼 추가
alter table delivery.user_order_id
add column store_id bigint(32) not null;

# user order id 테이블과 store 테이블간 연관관계 추가
ALTER TABLE delivery.user_order_id
ADD CONSTRAINT fk_store_user_order_id
FOREIGN KEY (store_id)
REFERENCES delivery.store (id);



