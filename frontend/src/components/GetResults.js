import React from 'react';
import axios from 'axios';
import './GetResults.css';

export default class GetResults extends React.Component {
  constructor(props) {
      super(props);
      this.state = {
        goals: [],
        finalGoals: {
            firstSent: [],
            remainder: []
        },
        value: ""
      }
  }

  spliceString = () => {
    var finalGoals = {
        firstSent: [],
        remainder: []
    };
      var i;
      for (i = 0; i < this.state.goals.length; i++) {
          var firstPeroid = this.state.goals[i].indexOf('.');
          finalGoals.firstSent.push(this.state.goals[i].slice(0,firstPeroid + 1));
          finalGoals.remainder.push(this.state.goals[i].slice(firstPeroid + 1));
      }
      this.setState({finalGoals});
  }

  handleSubmit = (event) => {
    event.preventDefault();
    let userSearch = this.state.value;
    if (userSearch === "") {
        alert("Please do not enter an empty field!");
    }
    else {
        axios.get(`http://localhost:8080/parser`, {params: {userSearch}})
        .then(res => {
            const goals = res.data;
            this.setState({ goals }, this.spliceString);
        })
    }
  }

  handleChange = (event) => {
    this.setState({value: event.target.value});
}


  render() {
    return (
        <body>
            <div className='goal'>
                <form onSubmit={ this.handleSubmit }>
                    <label for='input'>Enter A Goal You Wanna Achieve: </label>
                    <input type='text' id='input' value= {this.state.value} onChange={this.handleChange}></input>
                    <input type="submit" value="Submit"/>
                </form>
                <ol>
                    {/* { this.state.goals.map(goal => 
                    <li>{goal}</li>) } */}
                    {this.state.finalGoals.firstSent.map((fs, index) =>
                        <li>
                            <h3>{fs}</h3>
                            <p>{this.state.finalGoals.remainder[index]}</p>
                        </li>)}
                </ol>               
            </div>
        </body>
        
    )
  }
}