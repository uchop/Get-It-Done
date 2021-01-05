import React from 'react';
import axios from 'axios';
import './GetResults.css';

export default class GetResults extends React.Component {
  constructor(props) {
      super(props);
      this.state = {
        goals: [],
        value: ""
      }
  }

  handleChange = (event) => {
      this.setState({value: event.target.value});
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
            this.setState({ goals });
        })
    }
    
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
                    { this.state.goals.map(goal => <li>{goal}</li>) }
                </ol>               
            </div>
        </body>
        
    )
  }
}