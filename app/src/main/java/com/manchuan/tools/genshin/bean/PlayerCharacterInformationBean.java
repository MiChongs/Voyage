package com.manchuan.tools.genshin.bean;

import java.util.List;

public class PlayerCharacterInformationBean {

    private List<AvatarsBean> avatars;

    public List<AvatarsBean> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<AvatarsBean> avatars) {
        this.avatars = avatars;
    }

    public static class AvatarsBean {
        /**
         * id :
         * image :
         * icon :
         * name :
         * element :
         * fetter :
         * level :
         * rarity :
         * weapon :
         * reliquaries :
         * constellations :
         * actived_constellation_num :
         * costumes :
         */

        private int id;
        private String image;
        private String icon;
        private String name;
        private String element;
        private int fetter;
        private int level;
        private int rarity;
        private WeaponBean weapon;
        private int actived_constellation_num;
        private List<ReliquariesBean> reliquaries;
        private List<ConstellationsBean> constellations;
        private List<CostumesBean> costumes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getElement() {
            return element;
        }

        public void setElement(String element) {
            this.element = element;
        }

        public int getFetter() {
            return fetter;
        }

        public void setFetter(int fetter) {
            this.fetter = fetter;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getRarity() {
            return rarity;
        }

        public void setRarity(int rarity) {
            this.rarity = rarity;
        }

        public WeaponBean getWeapon() {
            return weapon;
        }

        public void setWeapon(WeaponBean weapon) {
            this.weapon = weapon;
        }

        public int getActived_constellation_num() {
            return actived_constellation_num;
        }

        public void setActived_constellation_num(int actived_constellation_num) {
            this.actived_constellation_num = actived_constellation_num;
        }

        public List<ReliquariesBean> getReliquaries() {
            return reliquaries;
        }

        public void setReliquaries(List<ReliquariesBean> reliquaries) {
            this.reliquaries = reliquaries;
        }

        public List<ConstellationsBean> getConstellations() {
            return constellations;
        }

        public void setConstellations(List<ConstellationsBean> constellations) {
            this.constellations = constellations;
        }

        public List<CostumesBean> getCostumes() {
            return costumes;
        }

        public void setCostumes(List<CostumesBean> costumes) {
            this.costumes = costumes;
        }

        public static class WeaponBean {
            /**
             * id :
             * name :
             * icon : https://upload-bbs.mihoyo.com/game_record/genshin/equip/UI_EquipIcon_Catalyst_Proto.png
             * type :
             * rarity :
             * level :
             * promote_level :
             * type_name : ??????
             * desc : ????????????????????????????????????????????????????????????????????????
             * affix_level :
             */

            private int id;
            private String name;
            private String icon;
            private int type;
            private int rarity;
            private int level;
            private int promote_level;
            private String type_name;
            private String desc;
            private int affix_level;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getRarity() {
                return rarity;
            }

            public void setRarity(int rarity) {
                this.rarity = rarity;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getPromote_level() {
                return promote_level;
            }

            public void setPromote_level(int promote_level) {
                this.promote_level = promote_level;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getAffix_level() {
                return affix_level;
            }

            public void setAffix_level(int affix_level) {
                this.affix_level = affix_level;
            }
        }

        public static class ReliquariesBean {
            /**
             * id : 96544
             * name : ????????????
             * icon : https://upload-bbs.mihoyo.com/game_record/genshin/equip/UI_RelicIcon_15022_4.png
             * pos : 1
             * rarity :
             * level :
             * set :
             * pos_name : ?????????
             */

            private int id;
            private String name;
            private String icon;
            private int pos;
            private int rarity;
            private int level;
            private SetBean set;
            private String pos_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getPos() {
                return pos;
            }

            public void setPos(int pos) {
                this.pos = pos;
            }

            public int getRarity() {
                return rarity;
            }

            public void setRarity(int rarity) {
                this.rarity = rarity;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public SetBean getSet() {
                return set;
            }

            public void setSet(SetBean set) {
                this.set = set;
            }

            public String getPos_name() {
                return pos_name;
            }

            public void setPos_name(String pos_name) {
                this.pos_name = pos_name;
            }

            public static class SetBean {
                /**
                 * id : 2150221
                 * name : ????????????
                 * affixes : [{"activation_number":2,"effect":"??????????????????15%???"},{"activation_number":4,"effect":"???????????????????????????????????????????????????????????????????????????????????????3?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????90%????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????3.5????????????????????????????????????????????????????????????30000??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"}]
                 */

                private int id;
                private String name;
                private List<AffixesBean> affixes;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<AffixesBean> getAffixes() {
                    return affixes;
                }

                public void setAffixes(List<AffixesBean> affixes) {
                    this.affixes = affixes;
                }

                public static class AffixesBean {
                    /**
                     * activation_number : 2
                     * effect : ??????????????????15%???
                     */

                    private int activation_number;
                    private String effect;

                    public int getActivation_number() {
                        return activation_number;
                    }

                    public void setActivation_number(int activation_number) {
                        this.activation_number = activation_number;
                    }

                    public String getEffect() {
                        return effect;
                    }

                    public void setEffect(String effect) {
                        this.effect = effect;
                    }
                }
            }
        }

        public static class ConstellationsBean {
            /**
             * id : 141
             * name : ????????????
             * icon : https://upload-bbs.mihoyo.com/game_record/genshin/constellation_icon/UI_Talent_S_Barbara_01.png
             * effect : ????????????10?????????1??????????????????
             * is_actived : true
             * pos : 1
             */

            private int id;
            private String name;
            private String icon;
            private String effect;
            private boolean is_actived;
            private int pos;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getEffect() {
                return effect;
            }

            public void setEffect(String effect) {
                this.effect = effect;
            }

            public boolean isIs_actived() {
                return is_actived;
            }

            public void setIs_actived(boolean is_actived) {
                this.is_actived = is_actived;
            }

            public int getPos() {
                return pos;
            }

            public void setPos(int pos) {
                this.pos = pos;
            }
        }

        public static class CostumesBean {
            /**
             * id : 201401
             * name : ????????????
             * icon : https://upload-bbs.mihoyo.com/game_record/genshin/costume/UI_AvatarIcon_BarbaraCostumeSummertime@2x.png
             */

            private int id;
            private String name;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
