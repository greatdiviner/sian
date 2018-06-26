# sian

JAXB cannot handle a nested java.util.Map correctly: 
Map<K, List<V>> listValuedMap, or Map<K, Set<V>> setValuedMap. 
To solve such problem, you have 2 choices:
1. workaround - introduce a intermediate class to hold the nested List or Set.
2. adapter - introduce a customized adapter.

GenericMapAdapter, GenericListValuedMapAdapter and GenericSetValuedAdapter are adapters for you to referrence.
