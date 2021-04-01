## 需求

[Todo Cli](https://time.geekbang.org/column/article/325594)


## 设计

![system design](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.github.com/robbietree/todo-cli/master/assets/design.puml)


## jar包形式运行

1. 编译打包

```shell
./build-jar.sh
```
2. 运行

```
❯ java -jar build/libs/todo-cli-*-all.jar -h
Usage: todo [-hV] [COMMAND]
...
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  add   Add todo item.
  list  List todo items.
  done  Complete one todo item

❯ java -jar build/libs/todo-cli-*-all.jar add test1

1. test1

Item 1 added

❯ java -jar build/libs/todo-cli-*-all.jar list
1. test1


Total: 1 items, 0 item done

❯ java -jar build/libs/todo-cli-*-all.jar list -a
1. [DONE] test1


Total: 1 items, 1 item done
```



## 本地可执行文件运行(bean依赖还有点问题, TODO)

```shell
./build-native.sh
```
