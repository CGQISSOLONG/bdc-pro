package com.admin.entity.primary;

import com.bdc.common.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName 类名：AdminMenu
 * @Description 功能说明：后台菜单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AdminMenu {

    private Integer id;
    /**
     * 链接
     */
    private String url;

    private String label;

    /**
     * 父节点的路径与父节点的id路径，用","分开，0表示父节点是根节点
     */
    private String parentIds = "0";

    @Setter
    private Integer rank;

    private Integer parentId;

    /**
     * 排序
     */
    private Integer orderNum = 1;

    /**
     * 扩展字段。菜单类型，供不同业务区分
     */
    private Integer type;

    /**
     * 样式，方便ui展现
     */
    private String style;

    /**
     * 状态 是否禁用
     */
    private String disabled;

    @Transient
    private AdminMenu parent;

    @Transient
    private List<? extends AdminMenu> childNodes = new ArrayList<>();


    public int getRank() {
        if (parentIds == null || "0".equals(parentIds)) {
            this.rank=1;
            return 1;
        }
        this.rank=parentIds.split(",").length;
        return parentIds.split(",").length;
    }


    public AdminMenu newChildNode(Integer nodeId, String label, int order) {
        AdminMenu node = new AdminMenu();
        node.parentIds = this.parentIds + "," + this.id;
        node.id = nodeId;
        node.orderNum = order;
        node.label = label;
        node.disabled = "0";
        node.parentId=this.id;
        return node;
    }

    public List<? extends AdminMenu> getChildNodes() {
        return childNodes;
    }

    /***
     * 以level==1的节点作为开始节点构建树结构
     * @param nodes
     * @return
     */
    public static List<? extends AdminMenu> buildTree(List<? extends AdminMenu> nodes) {
        if (isEmpty(nodes)) {
            return null;
        }
        //过滤出未禁用层级为1的菜单
        List<? extends AdminMenu> firstLevels =
                nodes.stream()
                        .filter(node -> Constants.NO.equals(node.getDisabled()) && ((AdminMenu) node).getRank() == 1)
                        .collect(Collectors.toList());
        sortByOrderNum(firstLevels);
        firstLevels.stream().forEach(node -> setChildren(node, nodes));
        return firstLevels;
    }

    /**
     * 迭代找出子节点
     * @param currentNode
     * @param nodeList
     */
    private static void setChildren(AdminMenu currentNode, List<? extends AdminMenu> nodeList) {
        List<? extends AdminMenu> childrens = nodeList.stream().filter(node -> (Constants.NO.equals(node.getDisabled())
                && ((AdminMenu) node).getParentIds().equals(currentNode.getParentIds() + "," + currentNode.getId())))
                .collect(Collectors.toList());
        if (isEmpty(childrens)) {
            return;
        }
        currentNode.childNodes = childrens;
        sortByOrderNum(childrens);
        childrens.stream().forEach(node -> setChildren(node, nodeList));

    }

    private static void sortByOrderNum(List<? extends AdminMenu> firstLevels) {
        firstLevels.sort(
                (node1, node2) -> Integer.valueOf(node1.getOrderNum()).compareTo(Integer.valueOf(node2.getOrderNum())));
    }


    /***
     * 按数结构给节点排序
     * @param nodes
     */
    public static void sortByTree(List<? extends AdminMenu> nodes) {
        if (isEmpty(nodes)) {
            return;
        }
        sortByOrderNum(nodes);
        nodes.sort((o1, o2) -> (o1.getParentIds() + "," + o1.getId()).compareTo(o2.getParentIds() + "," + o2.getId()));
    }

    private static boolean isEmpty(List nodes) {
        return nodes == null || nodes.isEmpty();
    }


    /**
     * 按节点的父子层次顺序展示
     * @param nodes
     */
    private static void printTreeToConsole(List<AdminMenu> nodes) {
        if (isEmpty(nodes)) {
            return;
        }
        sortByTree(nodes);
        nodes.stream().forEach(node -> {
            if (Constants.YES.equals(node.getDisabled())) {
                return;
            }
            for (int i = 1; i < node.getRank(); i++) {
                System.out.print("\t");
            }

            System.out.println(node);
        });
    }


    /**
     * 以第一层为起点，递归方式展示父子层次树
     * @param nodes
     */
    private static void printFirstLevelTreeToConsole(List<? extends AdminMenu> nodes) {
        if (isEmpty(nodes)) {
            return;
        }
        nodes.forEach(item -> {
            if (Constants.YES.equals(item.getDisabled())) {
                return;
            }
            for (int i = 1; i < item.getRank(); i++) {
                System.out.print("\t");
            }
            System.out.println(item);
            printFirstLevelTreeToConsole(item.getChildNodes());
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdminMenu Menu = (AdminMenu) o;
        return id != null ? id.equals(Menu.id) : Menu.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static void main(String[] arg){
        List<AdminMenu> nodes=new ArrayList<>();
        AdminMenu phone=new AdminMenu();
        phone.setId(6);
        phone.setLabel("手机");
        phone.setOrderNum(2);
        phone.setDisabled("0");
        nodes.add(phone);

        AdminMenu apple2=phone.newChildNode(66,"apple",2);
        nodes.add(apple2);
        nodes.add(phone.newChildNode(666,"oppo",2));
        nodes.add(phone.newChildNode(6666,"vivo",1));



        AdminMenu fruit=new AdminMenu();
        fruit.setId(1);
        fruit.setLabel("水果");
        fruit.setOrderNum(2);
        fruit.setDisabled("0");
        nodes.add(fruit);


        AdminMenu apple=fruit.newChildNode(7,"苹果",2);
        nodes.add(apple);
        nodes.add(apple.newChildNode(4,"红富士",2));
        nodes.add(apple.newChildNode(15,"山东苹果",1));

        AdminMenu lizi=fruit.newChildNode(8,"梨子",1);
        nodes.add(lizi);
        nodes.add(lizi.newChildNode(9,"雪梨",1));
        nodes.add(lizi.newChildNode(77,"鸭梨",2));

        AdminMenu shucai=new AdminMenu();
        shucai.setId(111);
        shucai.setLabel("蔬菜");
        shucai.setOrderNum(1);
        shucai.setDisabled("1");
        nodes.add(shucai);
        nodes.add(shucai.newChildNode(213,"白菜",2));
        printTreeToConsole(nodes);
        System.out.println("====================");
        List<? extends AdminMenu> tree= AdminMenu.buildTree(nodes);
        printFirstLevelTreeToConsole(tree);
    }
}
