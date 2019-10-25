package com.example.punch.contract;

/**
 * @author zzs
 * @date 2019/10/25 10:45
 */
public class CommonConstant {

    /** 存储方式 **/
    public enum StorageType {
        FILE("0", "file", "文件"),
        DB("1", "db", "数据库");

        private String code;
        private String nameEn;
        private String name;

        StorageType(String code, String nameEn, String name) {
            this.code = code;
            this.nameEn = nameEn;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getNameEn() {
            return nameEn;
        }

        public String getName() {
            return name;
        }

        public static String getCodeByNameEn(String nameEn) {
            StorageType[] values = StorageType.values();
            for (StorageType value : values) {
                if (value.getNameEn().equals(nameEn)) {
                    return value.getCode();
                }
            }
            return "";
        }

        public static String getNameByCode(String code) {
            StorageType[] values = StorageType.values();
            for (StorageType value : values) {
                if (value.getCode().equals(code)) {
                    return value.getNameEn();
                }
            }
            return null;
        }
    }
}
