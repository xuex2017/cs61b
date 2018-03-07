public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y){
        return (x - y == -1 || x - y == 1);
    }


/** below is useless thing
 *
 *
    public boolean isPalindrome(String word){
        boolean mark = false;
        int size = word.length();
        OffByOne o = new OffByOne();
        for(int i = 0; i < size/2; i++){
            if(o.equalChars(word.charAt(i),word.charAt(size-i))){
                mark = true;
            }
            else{
                mark = false;
                break;
            }
        }
        return mark;

    }
    **/
}
