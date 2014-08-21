import java.util.*;
import java.util.Queue;

/**
 * Created by ray on 2014-08-19.
 */
public class Collector {
    MapData _data;  //map data of each test case.
    State[][] _state;  //state of each cell in the process of searching.
    Queue<Position> _q;  //queue used by BFS
    int _step;  //current step away from start position
    boolean _find;

    /**
     * collect gas base on the mas data, algorithm is Dijkstra shortest path.
     * @param data
     */
    public Collector(MapData data){
        _data = data;

        doDijkstraSP();
    }

    /**
     * get search result
     * @return  if reach end positon, return collected gas, otherwise return impossible string.
     */
    public String GetResult(){
        String result = "";
        if(_find){
            result = Long.toString(_state[_data.Ex.X][_data.Ex.Y].Gas);
        }
        else{
            result = "Mission Impossible.";
        }
        return result;
    }

    /**
     * init state
     */
    private void initState(){
        _state = new State[_data.N][_data.M];
        for(int i=0; i<_data.N; i++){
            for(int j=0; j<_data.M; j++){
                State state = new State();
                state.Gas = 0;
                state.Step = Integer.MAX_VALUE;
                _state[i][j] = state;
            }
        }
        _step = 0;
        _find = false;
        _state[_data.En.X][_data.En.Y].Step = 0;
        _state[_data.En.X][_data.En.Y].Gas = _data.Data[_data.En.X][_data.En.Y];
    }

    /**
     * execute Dijkstra short path algorithm
     */
    private void doDijkstraSP(){
        //init state
        initState();

        //init queue and put start point into queue.
        _q = new LinkedList<Position>();
        _q.add(_data.En);


        //remove point from queue, and relax its adj.
        while (!_q.isEmpty()) {
            Position pos = _q.remove();

            //change to next step
            if(_step < _state[pos.X][pos.Y].Step){
                _step ++;
                //if find, break loop.
                if(_find) break;
            }

            //relax adjacents
            relaxAdjacent(pos, pos.X-1,pos.Y);
            relaxAdjacent(pos, pos.X+1,pos.Y);
            relaxAdjacent(pos, pos.X,pos.Y-1);
            relaxAdjacent(pos, pos.X,pos.Y+1);
        }

    }

    /**
     * rlax adjacent cell
     * @param pos  curent position
     * @param x
     * @param y
     */
    private void relaxAdjacent(Position pos,  int x, int y){
        if(x>=0 && x<_data.N && y>=0 && y<_data.M){  //invelidate x, y
            if(_data.Data[x][y] != -1){ //check if the cell is open space
                //relax cell only if cell's step is greater than current step
                if(_state[x][y].Step >= _step){  //if cell's step is greater than current step
                    _state[x][y].Step = _step+1;  //set step
                    long gas = _state[pos.X][pos.Y].Gas + _data.Data[x][y]; //collect gas

                    //push to queue only if the gas is empty, it means this cell is first time collect.
                    if(_state[x][y].Gas == 0 ){
                        _q.add(new Position(x,y));
                    }

                    //record greater gas
                    if(_state[x][y].Gas < gas ){
                        _state[x][y].Gas = gas;
                    }
                }

                //set find flag
                if(x == _data.Ex.X && y == _data.Ex.Y){
                    _find = true;
                }
            }
        }
    }

//    private ArrayList<Position> getAdjacent(Position pos){
//        ArrayList<Position> al = new ArrayList<Position>();
//        addPosition(al,pos.X-1,pos.Y);
//        addPosition(al,pos.X+1,pos.Y);
//        addPosition(al,pos.X,pos.Y-1);
//        addPosition(al,pos.X,pos.Y+1);
//        return al;
//    }
//
//    private void addPosition(ArrayList<Position> al, int x, int y){
//        if(x>=0 && x<_data.N && y>=0 && y<_data.M){
//            if(_data.Data[x][y] != -1){
//                al.add(new Position(x,y));
//                if(x == _data.Ex.X && y == _data.Ex.Y){
//                    _find = true;
//                }
//            }
//        }
//    }
//
//    private void relax(Position pos, Position adj){
//        if(_state[adj.X][adj.Y].Step > _step){
//            _state[adj.X][adj.Y].Step = _step+1;
//            long gas = _state[pos.X][pos.Y].Gas + _data.Data[adj.X][adj.Y];
//            if(_state[adj.X][adj.Y].Gas < gas ){
//                _state[adj.X][adj.Y].Gas = gas;
//            }
//            _q.add(adj);
//        }
//    }
}
