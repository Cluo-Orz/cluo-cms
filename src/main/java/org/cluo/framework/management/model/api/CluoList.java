package org.cluo.framework.management.model.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canfuu.cts
 * @class CluoListModel
 * @date 2023/6/5 22:57
 */
public class CluoList<T> {
    private final long total;
    private final List<T> data;

    public CluoList() {
        this.total = 0;
        this.data = new ArrayList<>();
    }
    public CluoList(long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public static <T> CluoList<T> of(long total, List<T> data) {
        return new CluoList<>(total, data);
    }

    public static <T> CluoList<T> of(long total, T... data) {
        return new CluoList<>(total, new ArrayList<>(List.of(data)));
    }

    public long getTotal() {
        return total;
    }

    public void add(T t) {
        data.add(t);
    }

    public void addAll(List<T> t) {
        data.addAll(t);
    }

    public void remove(T t) {
        data.remove(t);
    }

    public void removeAll(List<T> t) {
        data.removeAll(t);
    }

    public void clear() {
        data.clear();
    }

    public void set(int index, T t) {
        data.set(index, t);
    }

    public void setAll(List<T> t) {
        data.clear();
        data.addAll(t);
    }

    public void setAll(int index, List<T> t) {
        data.addAll(index, t);
    }

    public void add(int index, T t) {
        data.add(index, t);
    }

    public void remove(int index) {
        data.remove(index);
    }

    public void remove(int fromIndex, int toIndex) {
        data.subList(fromIndex, toIndex).clear();
    }

    public void removeLast() {
        data.remove(data.size() - 1);
    }

    public void removeFirst() {
        data.remove(0);
    }

    public void removeIf(java.util.function.Predicate<? super T> filter) {
        data.removeIf(filter);
    }

    public void removeIf(int fromIndex, int toIndex, java.util.function.Predicate<? super T> filter) {
        data.subList(fromIndex, toIndex).removeIf(filter);
    }

    public void removeIf(int fromIndex, java.util.function.Predicate<? super T> filter) {
        data.subList(fromIndex, data.size()).removeIf(filter);
    }

    public void removeIf(java.util.function.Predicate<? super T> filter, int toIndex) {
        data.subList(0, toIndex).removeIf(filter);
    }

    public void removeIf(java.util.function.Predicate<? super T> filter, int fromIndex, int toIndex) {
        data.subList(fromIndex, toIndex).removeIf(filter);
    }

    public T get(int index) {
        return data.get(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return data.subList(fromIndex, toIndex);
    }

    public List<T> subList(int fromIndex) {
        return data.subList(fromIndex, data.size());
    }



    public List<T> getData() {
        return data;
    }
}
