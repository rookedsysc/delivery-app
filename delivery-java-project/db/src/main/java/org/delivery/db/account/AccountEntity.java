package org.delivery.db.account;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;
import org.delivery.db.BaseEntity;

import lombok.experimental.SuperBuilder;





@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
// 부모로부터 상속받은 변수를 포함하겠다.
@SuperBuilder
@Table(name = "account")
public class AccountEntity extends BaseEntity {
}
