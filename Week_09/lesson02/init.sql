--db0
--账户表
CREATE TABLE t_account (
                             id int(11) NOT NULL AUTO_INCREMENT COMMENT '账户ID',
                             user_id int(11) NOT NULL COMMENT '用户ID',
                             usd_amount tinyint(1) NOT NULL COMMENT '美元余额',
                             chn_amount bigint(11) NOT NULL COMMENT '人民币余额',
                             update_time datetime DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin

insert into t_account (user_id, usd_amount, chn_amount) values('1','30','30');

--资金冻结表
CREATE TABLE t_assets_frozen (
                                   id int(11) NOT NULL AUTO_INCREMENT,
                                   tx_id bigint(11) NOT NULL COMMENT '交易ID',
                                   user_id int(11) NOT NULL COMMENT '用户id',
                                   account_type tinyint(4) NOT NULL COMMENT '账户类型  1：美元 2：人民币',
                                   amount bigint(11) NOT NULL COMMENT '冻结金额',
                                   status tinyint(1) NOT NULL COMMENT '1：准备  2：提交 3：无效',
                                   create_time datetime NOT NULL COMMENT '创建时间',
                                   update_time datetime DEFAULT NULL COMMENT '修改时间',
                                   PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin

--交易流水表
CREATE TABLE t_transaction_serial (
                                        id int(11) NOT NULL AUTO_INCREMENT,
                                        customer_userId bigint(11) NOT NULL COMMENT '购买人ID',
                                        customer_balance bigint(11) NOT NULL COMMENT '购买人消费金额',
                                        customer_account_type tinyint(1) NOT NULL COMMENT '购买人账户类型',
                                        boss_userId int(11) NOT NULL COMMENT '被购买人ID',
                                        boss_balance bigint(11) NOT NULL COMMENT '被购买人消费金额',
                                        boss_accountType tinyint(4) NOT NULL COMMENT '被购买人账户类型',
                                        tx_id bigint(11) NOT NULL COMMENT '事务ID',
                                        status tinyint(1) NOT NULL COMMENT '1:准备 2:完成 3:无效',
                                        create_time datetime NOT NULL COMMENT '创建时间',
                                        update_time datetime DEFAULT NULL COMMENT '更新时间',
                                        PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin


--db1

CREATE TABLE t_account (
                             id int(11) NOT NULL AUTO_INCREMENT COMMENT '账户ID',
                             user_id int(11) NOT NULL COMMENT '用户ID',
                             usd_amount tinyint(1) NOT NULL COMMENT '美元余额',
                             chn_amount bigint(11) NOT NULL COMMENT '人民币余额',
                             update_time datetime DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin

insert into t_account (user_id, usd_amount, chn_amount) values('2','30','30');