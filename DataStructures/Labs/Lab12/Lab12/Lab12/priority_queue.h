#ifndef priority_queue_h_
#define priority_queue_h_

#include <iostream>
#include <vector>
#include <cassert>


template <class T>
class priority_queue {
private:
    std::vector<T> m_heap;
    
public:
    priority_queue() {}
    
    priority_queue(std::vector<T>& values){
        for(int i = 0; i<values.size(); i++){
            push(values[i]);
        }
    }
    
    void percolate_down(){
        //Assumes element has already been added to the top of the chain for pop.
        int s = m_heap.size();
        T value = m_heap[0];            //storing value for swap later
        
        int child;                      //Child to compare against
        int i = 0;                      //Top of the tree (last element)
        while(((2*i)+1) < s){         //While left child exists
            if((2*i)+2 < s && m_heap[(2*i)+2] < m_heap[i] && m_heap[(2*i)+2]<m_heap[(2*i)+1]){
                child = (2*i)+2;            //Child is right
            }
            else{
                child = (2*i)+1;            //Child is left
            }
            
            if(m_heap[child] < m_heap[i]){
                m_heap[i] = m_heap[child];  //put child in parents spot
                m_heap[child] = value;      //put parent in childs spot
                
                i = child;                  //set iteration
            }
            else{
                break;
            }
        }
        
    }
    
    const T& top() const
    {
        assert( !m_heap.empty() );
        return m_heap[0];
    }
    
    void push( const T& entry )
    {
        m_heap.push_back(entry);
        int entry_i = (m_heap.size()-1);
        
        while(((entry_i-1)/2) >= 0){
            if(entry < m_heap[(entry_i-1)/2]){
                m_heap[entry_i] = m_heap[(entry_i-1)/2];    //Put parent in childs spot
                m_heap[(entry_i-1)/2] = entry;              //Put child in parents spot
                entry_i = (entry_i-1)/2;
            }
            else{
                break;
            }
        }
        
    }
    
    void pop()
    {
        assert( !m_heap.empty() );
        
        int s = m_heap.size();
        
        m_heap[0] = m_heap[s-1];        //Make first element the last element
        m_heap.pop_back();              //Remove last element
        
        percolate_down();
        
    }
    
    int size() { return m_heap.size(); }
    bool empty() { return m_heap.empty(); }
    
    
    //  The following three functions are used for debugging.
    
    //  Check to see that internally the heap property is realized.
    bool check_heap( )
    {
        return this->check_heap( this->m_heap );
    }
    
    //  Check an external vector to see that the heap property is realized.
    bool check_heap( const std::vector<T>& heap )
    {
        int s = heap.size();
        for(int i =0; i< heap.size(); i++){
            int left = (2*i)+1;
            int right = (2*i) + 1;
            
            if(left < s){
                if(heap[i] > heap[left]){
                    return false;
                }
            }
            if(right < s){
                if(heap[i] > heap[right]){
                    return false;
                }
            }
        }
        return true;
    }
    
    //  A utility to print the contents of the heap.  Use it for debugging.
    void print_heap( std::ostream & ostr )
    {
        for ( unsigned int i=0; i<m_heap.size(); ++i )
            ostr << i << ": " << m_heap[i] << std::endl;
    }
    
};

template <class T>
void heapify(std::vector<T> & v, int i) {
    // Find largest among root, left child and right child
    int n = v.size();
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    
    if (left < n && v[left] > v[largest])
        largest = left;
    
    if (right < n && v[right] > v[largest])
        largest = right;
    
    // Swap and continue heapifying if root is not largest
    if (largest != i) {
        std::swap(v[i], v[largest]);
        heapify(v, largest);
    }
}

template <class T>
void heap_sort( std::vector<T> & v ){
    int n = v.size();
    // Build max heap
    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(v, i);
    
    // Heap sort
    for (int i = n - 1; i >= 0; i--) {
        swap(v[0], v[i]);
        
        // Heapify root element to get highest element at root again
        heapify(v, 0);
    }
    
}

#endif
