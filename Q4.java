import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the length of the Array");
        int n=sc.nextInt();
        System.out.println("hiii");
        if(n!=0){
            System.out.println("Enter the elements of the Arrays");
        }
        Integer[] a=new Integer[n];
        for(int i=0; i<n; i++) {
            a[i]=sc.nextInt();
        }
        CrudOperation<Integer> operation=new CrudOperation<>(a);
        operation.eliminateInappropriatePairs();
        System.out.println(operation.toString());
    }
}

class CrudOperation<E extends Comparable<E>> {

    private E[] data;

    // This Constructor is Generic
    public CrudOperation(E[] data) {
        this.data = data;
    }

    /*
     * This method is for READ or GET element of Array
     * Param of read method is index of array
     */
    public E read(int index) {
        /*
         * implement this method
         */
        if (index < data.length) {
            return data[index];
        }
        return null;
    }

    /*
     * This method is for CREATE or INSERT element in Array
     */
    public void insert(E element) {
        /*
         * implement this method
         */
        // E[] newArray = new E[data.length+1];
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) Array.newInstance(Object.class, data.length + 1);
        for (int i = 0; i < this.data.length; i++) {
            newArray[i] = this.data[i];
        }
        newArray[this.data.length] = element;
        this.data = newArray;
    }

    /*
     * This method is for INSERT element in Array but with index
     */
    public void insert(E element, int index) {
        /*
         * implement this method
         */
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) Array.newInstance(Object.class, data.length + 1);
        for (int i = 0; i < index; i++) {
            newArray[i] = this.data[i];
        }
        newArray[index] = element;
        for (int i = index + 1; i < this.data.length + 1; i++) {
            newArray[i] = this.data[i - 1];
        }
        this.data = newArray;
    }

    /*
     * Update Your Array with set two params first param is index of element
     * that you want update this element and second param is new element
     */
    public E[] update(int index, E newElement) {
        /*
         * implement this method
         */
        this.data[index] = newElement;
        return data;
    }

    /*
     * Delete Element of Array with Index
     */
    public void delete(int index) {
        /*
         * implement this method
         */
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) Array.newInstance(Object.class, data.length - 1);
        for (int i = 0; i < index; i++) {
            newArray[i] = this.data[i];
        }
        for (int i = index; i < this.data.length - 1; i++) {
            newArray[i] = this.data[i + 1];
        }
        this.data = newArray;
    }

    /*
     * Delete Element of Array with element
     */
    public void delete(E element) {
        /*
         * implement this method
         */
        delete(getIndex(element));
        if(isOk(element)){
            delete(element);
        }
    }

    /*
     * Eliminate inappropriate pairs method
     */

    public E[] eliminateInappropriatePairs() {
        /*
         * implement this method
         */
        if(data.length!=0){
            if (this.data.length%2!=0){
                @SuppressWarnings("unchecked")
                E[] newArray = (E[])Array.newInstance(this.data[0].getClass(), this.data.length-1);
                for(int i=0;i<newArray.length;i++){
                    newArray[i] = this.data[i];
                }
                int num=0;
                int[] indexes = new int[newArray.length]; 
                for(int i=0;i<(newArray.length/2);i++){
                    if(newArray[2*i].compareTo(newArray[2*i+1])>0){
                        indexes[num]=2*i;
                        num++;
                        indexes[num]=2*i+1;
                        num++;
                    }
                }
                System.out.println(num);
                @SuppressWarnings("unchecked")
                E[] ans = (E[])Array.newInstance(this.data[0].getClass(), (newArray.length-num));
                int k=0,j=0;
                if(num!=0){
                    for(int i=0;i<newArray.length;i++){
                        if(i!=indexes[j]){
                            ans[k]=newArray[i];
                            k+=1;
                        } else{
                            j++;
                        }
                    }
                } else{
                    ans=newArray;
                }

                data=ans;
                return data;
            } else{
                @SuppressWarnings("unchecked")
                E[] newArray = (E[])Array.newInstance(this.data[0].getClass(), this.data.length);
                for(int i=0;i<newArray.length;i++){
                    newArray[i] = this.data[i];
                }
                int num=0;
                int[] indexes = new int[newArray.length]; 
                for(int i=0;i<(newArray.length/2);i++){
                    if(newArray[2*i].compareTo(newArray[2*i+1])>0){
                        indexes[num]=2*i;
                        num++;
                        indexes[num]=2*i+1;
                        num++;
                    }
                }
                @SuppressWarnings("unchecked")
                E[] ans = (E[])Array.newInstance(this.data[0].getClass(), (newArray.length-num));
                int k=0,j=0;
                if(num!=0){
                    for(int i=0;i<newArray.length;i++){
                        if(i!=indexes[j]){
                            ans[k]=newArray[i];
                            k+=1;
                        } else{
                            j++;
                        }
                    }
                } else{
                    ans=newArray;
                }

                data=ans;
                return data;
            }
        }
        return null;
    }

    public E[] getData() {
        return data;
    }

    public int getIndex(E element) {
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].equals(element)) {
                return i;
            }
        }
        return 0;
    }

    public boolean isOk(int index) {
        if (index >= 0 && index < this.data.length) {
            return true;
        }
        return false;
    }

    public boolean isOk(E element) {
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String ans="{";
        if(!Objects.isNull(data)){
            {
                if(data.length!=0){
                    for(int i=0; i<this.data.length-1; i++){
                        ans+=(this.data[i].toString()+",");
                    }
                    ans+=(this.data[this.data.length-1].toString());
                }
            }
        }
        ans+="}";
        return ans;
    }

}
