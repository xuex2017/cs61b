public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i ++){
            d.addLast(word.charAt(i));
        }
        return d;
    }



    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        boolean mark = false;

        while(d.size() > 1){
            if(d.getFirst() == d.getLast()){
                d.removeFirst();
                d.removeLast();
            }else{
                mark = false;
                break;
            }
        }
        if (d.size() == 0 || d.size() == 1) {
            mark = true;
        }
        return mark;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        boolean mark = false;

        while(d.size() > 1){
            if(cc.equalChars(d.getFirst(), d.getLast())){
                d.removeFirst();
                d.removeLast();
            }else{
                mark = false;
                break;
            }
        }
        if (d.size() == 0 || d.size() == 1) {
            mark = true;
        }
        return mark;
    }
}
