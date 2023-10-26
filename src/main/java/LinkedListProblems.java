import java.util.List;
import java.util.PriorityQueue;

public class LinkedListProblems {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }

        // useful in some scenarios
        public ListNode(int val, ListNode nextNode) {
            this.val = val;
            this.next = nextNode;
        }
    }

    /*
    Middle of the Linked List

    Given the head of a singly linked list, return the middle node of the linked list.
    If there are two middle nodes, return the second middle node.
     */

    /*
    Naive approach: Find the length of the linked list and then traverse again to the middle
    element.

    We can use space-time tradeoff technique to cut down the time.

    Related: Find the penultimate element in a linked list?
            Use two pointers ^
            You can generalize this for finding kth element from last.

    This is a TWO POINTER TECHNIQUE, when right pointer is at the last node,
    the first one should be at the node where we want it to be
     */
    // Time Complexity : O(n)
    // Space: O(1)
    public ListNode middleNode(ListNode head) {
        if (head == null)
            return null;
        ListNode hare = head;
        ListNode tortoise = head;
        // make hare jump two steps
        while (hare.next != null && hare.next.next != null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
        }
        // at this stage, tortoise will be at the middle node
        if (hare.next != null) {
            return tortoise.next; // even length list
        } else {
            return tortoise; // odd length list
        }
    }

    /*
    Linked List Cycle

    Given head, the head of a linked list, determine if the linked list has a cycle in it.

    There is a cycle in a linked list if there is some node in the
    list that can be reached again by continuously following the next pointer.
    Internally, pos is used to denote the index of the node that tail's next pointer
    is connected to. Note that pos is not passed as a parameter.

    Return true if there is a cycle in the linked list. Otherwise, return false.
     */

    /*
    Naive approach: Use a set (aux space) to track visited nodes
    TC = O(n)
    SC = O(n)
     */

    // O(1) solution, may do extra computations, i.e. may not find
    // cycle in first iteration, it might take extra iterations to detect cycle
    // Using two pointer approach: fast and slow, hare and tortoise
    // Once two pointers enter the cycle, they will meet at some point if we keep moving forward

    // SC: O(n)
    // TC: O(n + x) = O(n) <-- x will always be less than n
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false; // empty list

        ListNode hare = head;
        ListNode tortoise = head;
        // make hare jump two steps
        while (hare.next != null && hare.next.next != null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
            if (hare == tortoise) {
                return true;
            }
        }

        // If we come here, that means hare reach end of the list
        // and there is no cycle
        return false;
    }

    /*
    Happy Number

    Write an algorithm to determine if a number n is happy.

    A happy number is a number defined by the following process:

    Starting with any positive integer, replace the number by the sum of the squares of its digits.
    Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
    Those numbers for which this process ends in 1 are happy.

    Return true if n is a happy number, and false if not.
     */
    // TC: O(1), cycle won't be long than 800 or 1000 or Log(n)
    // SC: O(1)
    public boolean isHappy(int n) {
        // following same pattern of fast and slow pointer technique
        int hare = n;
        int tortoise = n;
        while (true) { // there will alway be next number according to the computation
            // move hare two steps forward, hence two function calls
            hare = sumOfSqOfDigits(sumOfSqOfDigits(hare)); // here function is square of digits
            tortoise = sumOfSqOfDigits(tortoise); // move one step forward
            if (hare == tortoise) {
                return hare == 1; // if it is one, then its happy, otherwise there is a cycle
            }
        }
    }
    // calculate sum of squares of the digits
    private int sumOfSqOfDigits(int num) {
        int sumOfSquares = 0;
        while (num != 0) {
            int digit = num % 10;
            num = num / 10;
            sumOfSquares += digit * digit;
        }
        return sumOfSquares;
    }

    /*
    Linked List Cycle II

    Given the head of a linked list, return the node where the cycle begins.
    If there is no cycle, return null.

    There is a cycle in a linked list if there is some node in the list that can be
    reached again by continuously following the next pointer.
    Internally, pos is used to denote the index of the node that tail's next pointer is
    connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

    Do not modify the linked list.
     */

    /*
    When hare and tortoise meet in the cycle,
        Tortoise has traveled "start + i" edges
        Hare has traveled 2(start + i) edges

     */
    // TC: O(n)
    // SC: O(1)
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null; // empty list

        ListNode hare = head;
        ListNode tortoise = head;
        // make hare jump two steps
        while (hare.next != null && hare.next.next != null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
            if (hare == tortoise) { // cycle found
                ListNode third = head;
                while (third != tortoise) {
                    third = third.next;
                    tortoise = tortoise.next;
                } // they will meet at the beginning of the cycle
                return third;
            }
        }

        // If we come here, that means hare reach end of the list
        // and there is no cycle
        return null;
    }

    /*
    Circular Array Loop

    You are playing a game involving a circular array of non-zero integers nums.
    Each nums[i] denotes the number of indices forward/backward you must move
    if you are located at index i:

    If nums[i] is positive, move nums[i] steps forward, and
    If nums[i] is negative, move nums[i] steps backward.

    Since the array is circular, you may assume that moving forward from
     the last element puts you on the first element,
     and moving backwards from the first element puts you on the last element.

    A cycle in the array consists of a sequence of indices seq of length k where:

    Following the movement rules above results in the repeating index sequence
    seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...

    Every nums[seq[j]] is either all positive or all negative.
    k > 1

    Return true if there is a cycle in nums, or false otherwise.
     */
    public boolean circularArrayLoop(int[] nums) {

        // cycle can start at any index, we can try all possibilities
        // , starting at every index and see if ends up in cycle

        for (int i = 0; i < nums.length; i++) {
            // hare and tortoise start at the same index
            int hare = i;
            int tortoise = i;
            while (true) { // there is always an index to move in circular array
                hare = fn(fn(hare, nums), nums);
                tortoise = fn(tortoise, nums);
                if (hare == tortoise) { // cycle detected
                    // check if it is a special cycle, properties such as
                    //   length > 1
                    //   all numbers positive or all numbers are negative in the cycle
                    int third = tortoise;
                    int count = 1;

                    boolean positive = false; // is it cycle with positive numbers
                    if (nums[third] > 0)
                        positive = true;

                    while (fn(third, nums) != tortoise) {
                        third = fn(third, nums);
                        count++;
                        if ((nums[third] < 0 && positive) || (nums[third] > 0 && !positive)) {
                            count = 1;
                            break; // invalid cycle, hence stop
                        }
                    }
                    // count stores cycle length
                    if (count > 1)
                        return true;
                    else
                        break; // start another search for start of the cycle
                }
            }
        }
        return false;
    }

    private int fn(int i, int[] nums) {
        int n = nums.length;
        return i + nums[i] >= 0 ? (i + nums[i]) % n: n + ((i + nums[i]) % n); // move nums[x] positions to the right
    }

    /*
    Find the Duplicate Number

    Given an array of integers nums containing n + 1 integers where each integer is in
    the range [1, n] inclusive.

    There is only one repeated number in nums, return this repeated number.

    You must solve the problem without modifying the array nums and uses only constant
     extra space.

    Example 1:
    Input: nums = [1,3,4,2,2]
    Output: 2

    Example 2:
    Input: nums = [3,1,3,4,2]
    Output: 3
     */
    public int findDuplicate(int[] nums) {
        int h = 0; // hare
        int t = 0; // tortoise
        while (true) {
            h = fn2(fn2(h, nums), nums);
            t = fn2(t, nums);
            if (h == t) { // cycle detected
                // find the index where cycle began
                int j = 0; // new tortoise
                while (j != t) {
                    j = fn2(j, nums);
                    t = fn2(t, nums);
                }
                return t;
            }
        }
    }

    private int fn2(int x, int[] nums) {
        return nums[x];
    }

    /*
    Design Linked List

    Design your implementation of the linked list. You can choose to use a singly or
    doubly linked list.
    A node in a singly linked list should have two attributes: val and next.
    val is the value of the current node, and next is a pointer/reference to the next node.
    If you want to use the doubly linked list, you will need one more attribute prev to
    indicate the previous node in the linked list.
    Assume all nodes in the linked list are 0-indexed.

    Implement the MyLinkedList class:

    MyLinkedList() Initializes the MyLinkedList object.
    int get(int index) Get the value of the indexth node in the linked list.
                       If the index is invalid, return -1.
    void addAtHead(int val) Add a node of value val before the first element of the linked list.
                After the insertion, the new node will be the first node of the linked list.
    void addAtTail(int val) Append a node of value val as the last element of the linked list.
    void addAtIndex(int index, int val) Add a node of value val before the indexth node
            in the linked list. If index equals the length of the linked list,
            the node will be appended to the end of the linked list.
            If index is greater than the length, the node will not be inserted.
    void deleteAtIndex(int index) Delete the indexth node in the linked list,
            if the index is valid.
     */

    public static class MyLinkedList {
        ListNode head;
        ListNode tail;
        // ListNode sentinel;
        // Tip: For linked list problems, use a sentinel node to avoid edge cases such as
        //      - head node changes (because of insertion or deletion or rearragements)
        //            - insertion at head
        //            - deleting head
        //      - building a new list

        // create empty list
        public MyLinkedList() {
            head = null;
            tail = null;
        }

        // This is search function
        public int get(int index) {
            ListNode curr = head;
            int nodeIndex = 0;
            while (curr != null && nodeIndex != index) {
                curr = curr.next;
                nodeIndex++;
            }
            if (curr != null) {
                return curr.val;
            } else {
                return -1; // invalid node
            }
        }

        public void addAtHead(int val) {
            ListNode node = new ListNode(val);
            node.next = head;
            // manipulate head and tail (if single node), after every list manipulation
            head = node;
            if (tail == null) { // if list was empty, this is the first node to be added
                tail = node;
            }
        }

        // always draw a diagram for correct pointer manipulation
        public void addAtTail(int val) {
            ListNode node = new ListNode(val);
            if (tail == null) { // list is empty
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        // Insertion
        // 1. Get to the place (search)
        // 2. Do the insertion, you will be inserting before the node you find, hence need prev pointer
        public void addAtIndex(int index, int val) {
            // create a sentinel node, which always points to head
            ListNode sentinel = new ListNode(-Integer.MAX_VALUE, head);
            ListNode pred = sentinel;
            // search for a node at that index first, keep track of prev pointer
            ListNode curr = head;
            int nodeIndex = 0;
            while (curr != null && nodeIndex != index) {
                pred = pred.next;
                curr = curr.next;
                nodeIndex++;
            }
            // if index is greater than length of the list, do not insert
            // sentinel helps if curr is null at this point
            ListNode newNode = new ListNode(val, null);
            if (curr != null) {
                pred.next = newNode;
                newNode.next = curr;
                // update tail only if we are inserting at the end=
            } else if (nodeIndex == index) {
                pred.next = newNode;
                tail = newNode;
            }
            head = sentinel.next; // always will be this as last line
        }

        // Deletion:
        // 1. Search for the node to find
        // 2. Extract it, you will need to connect pred -> next
        public void deleteAtIndex(int index) {
            ListNode sentinel = new ListNode(-Integer.MAX_VALUE, head);
            ListNode pred = sentinel;
            ListNode curr = head;
            int nodeIndex = 0;
            while (curr != null && nodeIndex != index) {
                pred = pred.next;
                curr = curr.next;
                nodeIndex++;
            }
            // delete curr node if it is not null
            if (curr != null) {
                pred.next = curr.next;
                if (tail == curr) {
                    tail = pred;
                    if (tail == sentinel) // if there was only one node, and it is deleted
                        tail = null;
                }
            }
            head = sentinel.next;
        }
    }

    /*
    Delete Node in a Linked List

    There is a singly-linked list head and we want to delete a node node in it.
    You are given the node to be deleted node. You will not be given access to the first node of head.
    All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.
    Delete the given node.
    */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /*
    Remove Linked List Elements

    Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
     */
    // TC: O(n)
    // SC: O(1)
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(-Integer.MAX_VALUE);
        dummy.next = head;
        ListNode pred = dummy;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == val) { // remove node
                pred.next = curr.next;
                curr = curr.next;
            } else {
                curr = curr.next;
                pred = pred.next;
            }
        }
        return dummy.next;
    }

    /*
    Remove Duplicates from Sorted List

    Given the head of a sorted linked list, delete all duplicates such
    that each element appears only once.
    Return the linked list sorted as well.
     */
    // TC: O(n)
    // SC: O(1)
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-Integer.MAX_VALUE);
        dummy.next = head;
        ListNode curr = head;
        ListNode pred = dummy;
        while (curr != null) {
            // work to be done on current node
            if (pred.val == curr.val) { // delete curr node
                pred.next = curr.next;
                curr = curr.next;
            } else {
                curr = curr.next;
                pred = pred.next;
            }
        }
        return dummy.next;
    }

    /*
    Remove Duplicates from Sorted List II

    Given the head of a sorted linked list,
    delete all nodes that have duplicate numbers,
    leaving only distinct numbers from the original list.
    Return the linked list sorted as well.
     */
    // TC: O(n)
    // SC: O(1)
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-Integer.MAX_VALUE);
        dummy.next = head;
        ListNode curr = head;
        ListNode pred = dummy;
        while (curr != null) {
            // work to be done at this node
            if (curr.next != null && curr.val == curr.next.val) {
                ListNode p = curr.next;
                while (p != null && curr.val == p.val) {
                    p = p.next;
                }
                pred.next = p; // Long jump for the pointer
                curr = p;
            } else {
                pred = pred.next;
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    /*
    Remove Nth Node From End of List

    Given the head of a linked list,
    remove the nth node from the end of the list and return its head.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-Integer.MAX_VALUE);
        dummy.next = head;
        ListNode leader = dummy; // THIS IS IMPORTANT, pointing to dummy
        ListNode follower = dummy;
        int c = 0;
        while (c < n) {
            leader = leader.next;
            c++;
        }
        // distance between them is now N
        ListNode pred = null;
        while (leader != null) {
            leader = leader.next;
            pred = follower;
            follower = follower.next;
        }
        pred.next = follower.next;
        return dummy.next;
    }

    /*
    Delete N Nodes After M Nodes of a Linked List

    You are given the head of a linked list and two integers m and n.

    Traverse the linked list and remove some nodes in the following way:

    Start with the head as the current node.
    Keep the first m nodes starting with the current node.
    Remove the next n nodes
    Keep repeating steps 2 and 3 until you reach the end of the list.

    Return the head of the modified list after removing the mentioned nodes.
     */
    public ListNode deleteNodes(ListNode head, int m, int n) {

        return null;
    }

    /*
    Merge Two Sorted Lists

    You are given the heads of two sorted linked lists list1 and list2.

    Merge the two lists into one sorted list.
    The list should be made by splicing together the nodes of the first two lists.
    Return the head of the merged linked list.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-Integer.MAX_VALUE);
        ListNode tail = dummy;
        while (l1 != null & l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                tail = tail.next;
                l1 = l1.next;
                tail.next = null; // detach from l1, may not be necessary
            } else {
                tail.next = l2;
                tail = tail.next;
                l2 = l2.next;
                tail.next = null;
            }
        }
        // connect remaining non-null list
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        return dummy.next;
    }

    /*
    Sort List

    Given the head of a linked list, return the list after sorting it in ascending order.
     */
    public ListNode sortList(ListNode head) {
        return sortListHelper(head);
    }
    // divide and conquer approach
    private ListNode sortListHelper(ListNode h) {
        // base case
        if (h == null || h.next == null) { // single node or empty list
            return h;
        }
        // recursive case: divide the list in the middle
        // use fast and slow pointer technique
        ListNode slow = h;
        ListNode fast = h;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow points to the middle node (for even list)
        ListNode h2 = slow.next; // second half of the list, original head h points to first half
        slow.next = null; // break two lists

        // recursively divide and conquer
        ListNode l1 = sortListHelper(h);
        ListNode l2 = sortListHelper(h2);

        // merge two sorted lists l1 and l2
        return mergeTwoLists(l1, l2);
    }

    /*
    Merge k Sorted Lists

    You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
    Merge all the linked-lists into one sorted linked-list and return it.
     */
    // TC(n,k) = O(nk * logk)
    public ListNode mergeKLists(ListNode[] lists) {
        // use min-heap
        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for (ListNode head: lists) {
            if (head != null) {
                pq.offer(head); // add heads of all lists to pq
            }
        }

        ListNode dummy = new ListNode(-Integer.MAX_VALUE); // sentinel
        ListNode tail = dummy; // to traverse
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = node;
            if (node.next != null) {
                pq.offer(node.next);
            }
            tail.next = null; // break the link
        }
        return dummy.next;
    }
    /*
    Insertion Sort List

    Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.

    The steps of the insertion sort algorithm:
        Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
        At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
        It repeats until no input elements remain.
     */
    // TC: O(n^2)
    // SC: O(1)
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1); // head of sorted list
        ListNode curr = head;
        while (curr != null) {
            // do something with the current node
                // 1. Extract the current node
                // 2. Insert into sorted list
                // 3. Increment the curr node

            // 1. Extract
            ListNode succ = curr.next;
            curr.next = null; // extract curr node

            // 2. Insert
            ListNode pred = dummy;
            ListNode tmp = dummy.next;
            while (tmp != null && tmp.val < curr.val) {
                pred = tmp;
                tmp = tmp.next;
            }
            pred.next = curr; // attach the extracted node
            curr.next = tmp; // connect lists

            // 3. Increment
            curr = succ;
        }
        return dummy.next;
    }

    /*
    Partition List

    Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

    You should preserve the original relative order of the nodes in each of the two partitions.
     */
    public ListNode partition(ListNode head, int x) {

        return null;
    }

    /*
    Rotate List

    Given the head of a linked list, rotate the list to the right by k places.
     */
    public ListNode rotateRight(ListNode head, int k) {

        return null;
    }


}
